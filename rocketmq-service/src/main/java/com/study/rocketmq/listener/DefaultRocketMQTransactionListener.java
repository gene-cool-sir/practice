package com.study.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gene ,创建生产者,并发送事务性消息
 *  保证消息的从本地---> 到发送到broker阶段的事务一致性
 *  rocketMQTemplateBeanName: 由不同的RocketMQTemplate对象,处理不同事务消息逻辑
 *
 *  在 RocketMQ 中，事务消息的状态包括以下几种：
 * Commit（已提交）： 事务消息已经成功被提交。
 * Rollback（已回滚）： 事务消息已经被回滚。
 * Unknown（未知）： 事务消息的状态未知。这通常发生在 Broker 收到了提交确认但是无法获取到本地事务的状态时。
 *
 * 当一个事务消息的状态为 Unknown 时，RocketMQ 会根据以下配置进行重试(需要再broker.conf中进行配置)：
 * transactionCheckInterval（事务状态检查间隔）： 配置检查事务消息状态的时间间隔，默认为 60 秒。重试次数依据MQ发送消息配置的最大失败次数
 * checkThreadPoolMinSize 和 checkThreadPoolMaxSize（检查事务状态的线程池大小）： 配置用于检查事务状态的线程池的最小和最大线程数。
 * checkRequestHoldMax（检查请求的最大持有数量）： 配置每次检查事务状态时，最多持有的检查请求数量，默认为 2000。
 * transactionTimeout（事务超时时间）： 配置事务消息的超时时间，默认为 6 秒。
 * # 事务消息发送失败时的最大重试次数，默认值为 16
 * checkTimesWhenSendFailed=16
 * # 事务消息回查时的最大重试次数，默认值为 16
 * checkTimesWhenSendBack=16
 */
@Slf4j
@Component
@RocketMQTransactionListener(rocketMQTemplateBeanName ="rocketMQTemplate")
public class DefaultRocketMQTransactionListener implements RocketMQLocalTransactionListener {

    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        System.out.println("我正在尝试提交我本地的message信息到broker:" + msg);
        System.out.println("我刚才提交的额外对象obj:" + arg);
        System.out.println("status:" + status);
        if (value == 4) {
            return RocketMQLocalTransactionState.ROLLBACK; // 丢弃消息
        }
        if (value == 0 || value == 6) {
            return RocketMQLocalTransactionState.COMMIT; // 直接提交消息
        }
        // 测试消息事务会查, 通过会查提交
        localTrans.put(String.valueOf(msg.getHeaders().get(RocketMQUtil.toRocketHeaderKey(RocketMQHeaders.TRANSACTION_ID))), status);
        return RocketMQLocalTransactionState.UNKNOWN; // 回查消息
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
         Integer status = localTrans.get(String.valueOf(msg.getHeaders().get(RocketMQUtil.toRocketHeaderKey(RocketMQHeaders.TRANSACTION_ID))));
        if (Objects.nonNull(status)) {
            switch (status) {
                case 0:
                    return RocketMQLocalTransactionState.UNKNOWN; // 等着再次执行checkLocalTransaction(看是否可以执行成功)
                case 2:
                    return RocketMQLocalTransactionState.ROLLBACK; // 放弃
                default:
                    return RocketMQLocalTransactionState.COMMIT;
            }
        }
        //丢弃
        return RocketMQLocalTransactionState.ROLLBACK;
    }

}
