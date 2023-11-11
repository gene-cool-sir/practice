package com.study.rocketmq.listener;

import com.alibaba.fastjson.JSONObject;
import com.study.rocketmq.domain.BaseMqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.util.StopWatch;

/**
 * 抽象消息监听器，封装自己对消息处理,重试等额外的公共处理业务，如
 * 1、基础日志记录
 * 2、异常处理
 * 3. 消息是否重试逻辑控制
 * 4、警告通知
 * @author gene
 */
@Slf4j
public abstract class BaseConsumerMessageListener<T extends BaseMqMessage> {

    /**
     * 消息者名称
     *
     * @return 消费者名称
     */
    protected abstract String consumerName();

    /**
     * 消息处理
     *
     * @param message 待处理消息
     * @throws Exception 消费异常
     */
    protected abstract void handleMessage(T message) throws Exception;

    /**
     * 消费者超过最大消费重试次数消息，是否需要进行额外的操作,比如发送告警,人工处理
     *
     * @param message 待处理消息
     */
    protected abstract void overMaxRetryTimesMessage(T message);

    /**
     * 是否过滤消息，例如某些业务数据
     *
     * @param message 待处理消息
     * @return true: 本次消息被过滤，false：不过滤
     */
    protected boolean isFilter(T message) {
        return false;
    }

    /**
     * 消费异常时是否抛出异常
     *
     * @return true: 抛出异常(交给RocketMQ进行重试)，
     *         false：消费异常(如果没有开启重试则消息会被自动ack)
     */
    protected abstract boolean isThrowException();


    /**
     * 消费者达到最大消费次数消费失败, overMaxRetryTimesMessage() 处理之后,消息是否还交给broker放入死信队列
     *
     * @return true: 达到最大重试次数,最总不放入死信队列
     *         false：达到最大重试次数,放入死信队列
     */
    protected boolean putDLQ() {
        return false;
    }

    /**
     * 由父类来完成基础的日志和调配，下面的只是提供一个思路
     */
    public void dispatchMessage(T message) {
        log.info("记录消息数据开始[{}]", message.getTraceId());

        log.info("[{}]消费者收到消息[{}]", consumerName(),
                JSONObject.toJSONString(message));
        if (isFilter(message)) {
            log.info("消息不满足消费条件，已过滤");
            return;
        }

        try {
            StopWatch sw = new StopWatch(message.getTraceId());
            sw.start();
            handleMessage(message);
            sw.stop();
            log.info("消息处理成功，耗时[{}ms]", sw.getTotalTimeMillis());
        } catch (Exception e) {

            // 获取子类RocketMQMessageListener定义消费者的最大重试次数
            RocketMQMessageListener annotation = this.getClass().getAnnotation(RocketMQMessageListener.class);
            // 超过最大重试次数时调用子类方法处理,Broker不会再次重新把消息放入重试队列%RETRY%, 消息会进入死信队列
            if (message.getRetryTimes() >= annotation.maxReconsumeTimes()) {
                overMaxRetryTimesMessage(message); // 交给自己处理,不进行放入死信队列
                if (!putDLQ()) {
                    return;
                }
            }

            // fixme: 通过异常类型,进行不同逻辑处理

            // 是捕获异常还是抛出，由子类决定
            if (isThrowException()) {
                message.setRetryTimes(message.getRetryTimes() + 1);
                log.error("消息消费异常,等待broker发起消息重试", e);
                throw new RuntimeException(e);
            }

            // 捕获了异常,默认信息进行了ack处理(表示消息处理成功)
            log.error("消息处理失败:放弃broker的重试消息:message=[{}]",
                    JSONObject.toJSONString(message),
                    e);
        }
    }
}
