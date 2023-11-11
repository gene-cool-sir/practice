package com.study.rocketmq.listener;

import com.study.rocketmq.domain.BaseMqMessage;
import com.study.rocketmq.domain.RocketMqBizConstant;
import com.study.rocketmq.servicebus.ServiceBusMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Created by gene 消费消息, 创建消费者实例并监听消费
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC,
        consumerGroup = RocketMqBizConstant.SERVICE_BUS_TEST_CONSUME_GROUP,
        selectorExpression = RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG,
        consumeMode = ConsumeMode.CONCURRENTLY,
        messageModel = MessageModel.CLUSTERING,
        maxReconsumeTimes = 2, // 消息消费失败重发次数,默认16次
        consumeTimeout = 5L, // 阻塞消费的最长时间默认15分钟
        replyTimeout = 3000, // 重试超时时间
        enableMsgTrace = false, // 是否开启消费轨迹
        delayLevelWhenNextConsume = 5, // -1,不重试,直接放入DLQ 0,broker控制重试频率 >0,客户端控制重试频率
        // 指定消费者线程数，默认64，生产中请注意配置，避免过大或者过小
        consumeThreadMax = 5,
        suspendCurrentQueueTimeMillis = 20000, //有序模式暂停拉取的时间间隔，单位毫秒。最小值为 10，最大值为 30000。
        awaitTerminationMillisWhenShutdown  = 1000 //关闭消费者时等待消息消耗的最长时间，以毫秒为单位。
)
public class CustomRocketMQListener extends BaseConsumerMessageListener implements RocketMQListener<ServiceBusMessage> {

    @Override
    public void onMessage(ServiceBusMessage message) {
        // 此时这里没有直接处理业务，而是先委派给父类做基础操作，然后父类做完基础操作后会调用子类的实际处理类型
        System.out.println("消费处理开始:");
        super.dispatchMessage(message);
        System.out.println("消费消息完成:");
    }

    @Override
    protected String consumerName() {
        return "自定义消费";
    }

    @Override
    protected void handleMessage(BaseMqMessage message) throws Exception {
        System.out.println("消费处理中:" + message);
    }

    @Override
    protected void overMaxRetryTimesMessage(BaseMqMessage message) {
        System.out.println("消费消费达到最大重试次数:" + message.getRetryTimes());
    }

    @Override
    protected boolean isThrowException() {
        return false;
    }
}
