/*
package com.study.rocketmq.servicebus;

import com.suunto.platform.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

import java.util.Objects;

*/
/**
 * Created by gene
 *//*

@Slf4j
public class ServiceBusSenderClient {

    private String queueName;
    private static RocketMQTemplate mqTemplate;

    ServiceBusSenderClient(String queueName) {
        this.queueName = queueName;
    }

    public ServiceBusMessageBatch createMessageBatch() {
        return new ServiceBusMessageBatch();
    }

    private RocketMQTemplate rocketMQTemplate() {
        if (mqTemplate == null) {
            mqTemplate = SpringContextHolder.getBean("rocketMQTemplate");
        }

        return mqTemplate;
    }

    public void sendMessage(ServiceBusMessage message) {
        SendResult sendResult = rocketMQTemplate().syncSend(queueName, Objects.requireNonNull(message, "Message cannot be null."));
        checkStatus(sendResult);
    }

    public void sendMessages(ServiceBusMessageBatch batch) {
        SendResult sendResult = rocketMQTemplate().syncSend(queueName, Objects.requireNonNull(batch, "Message cannot be null.").getServiceBusMessageList());
        checkStatus(sendResult);
    }

    private void checkStatus(SendResult sendResult) {
        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            log.debug("Message acknowledged from RocketMQ: Id = {}", sendResult.getMsgId());
        } else {
            log.error("Message send field from RocketMQ: Id = {}; status = {}", sendResult.getMsgId(), sendResult.getSendStatus());
        }
    }
}
*/
