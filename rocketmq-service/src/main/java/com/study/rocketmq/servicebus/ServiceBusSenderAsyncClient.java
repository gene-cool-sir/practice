package com.study.rocketmq.servicebus;

import com.study.rocketmq.component.SpringContextHolder;
import com.study.rocketmq.template.DefaultRocketMQTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

import java.util.Objects;

/**
 * Created by gene
 */
@Slf4j
public final class ServiceBusSenderAsyncClient {

    /**
     * destination : topic + tag  = Azure Service Bus 的 queueName
     */
    private String destination;

    private DefaultRocketMQTemplate defaultRocketMQTemplate;

    ServiceBusSenderAsyncClient(String destination) {
        this.destination = destination;
        rocketMQTemplate();
    }
    
    public void sendMessage(ServiceBusMessage message) {
        defaultRocketMQTemplate.getTemplate()
                .asyncSend(destination, Objects.requireNonNull(message, "Message cannot be null."),
                new AsyncSendResult());
    }

    private DefaultRocketMQTemplate rocketMQTemplate() {
        if (defaultRocketMQTemplate == null) {
            defaultRocketMQTemplate = SpringContextHolder.getBean("defaultRocketMQTemplate");
        }
        return defaultRocketMQTemplate;
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
    }
}
