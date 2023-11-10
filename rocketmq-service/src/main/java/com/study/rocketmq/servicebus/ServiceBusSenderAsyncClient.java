package com.study.rocketmq.servicebus;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * Created by gene
 */
@Slf4j
public final class ServiceBusSenderAsyncClient {
    private String queueName;
    private static RocketMQTemplate mqTemplate;

    ServiceBusSenderAsyncClient(String queueName) {
        this.queueName = queueName;
    }
    
   /* public Mono<Void> sendMessage(ServiceBusMessage message) {
        rocketMQTemplate().asyncSend(queueName, Objects.requireNonNull(message, "Message cannot be null."), new AsyncSendResult());
        return Mono.empty();
    }

    private RocketMQTemplate rocketMQTemplate() {
        if (mqTemplate == null) {
            mqTemplate = SpringContextHolder.getBean("rocketMQTemplate");
        }

        return mqTemplate;
    }

    public static final class AsyncSendResult implements SendCallback {

        @Override
        public void onSuccess(SendResult sendResult) {
            log.debug("Message acknowledged from RocketMQ: Id = {}", sendResult.getMsgId());
        }

        @Override
        public void onException(Throwable throwable) {
            log.error("Unable to send external message to RocketMQ", throwable);
        }
    }*/
}
