package com.study.rocketmq.servicebus;

import com.study.rocketmq.component.SpringContextHolder;
import com.study.rocketmq.template.DefaultRocketMQTemplate;
import com.study.rocketmq.template.ListSplitter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author gene
 */
@Slf4j
public class ServiceBusSenderClient {

    /**
     * destination : topic + tag  = Azure Service Bus 的 queueName
     */
    private String destination;

    private DefaultRocketMQTemplate defaultRocketMQTemplate;

    ServiceBusSenderClient(String destination) {
        this.destination = destination;
        rocketMQTemplate();
    }

    public ServiceBusMessageBatch createMessageBatch() {
        return new ServiceBusMessageBatch();
    }

    private DefaultRocketMQTemplate rocketMQTemplate() {
        if (defaultRocketMQTemplate == null) {
            defaultRocketMQTemplate = SpringContextHolder.getBean("defaultRocketMQTemplate");
        }

        return defaultRocketMQTemplate;
    }

    public void sendMessage(ServiceBusMessage message) {
        SendResult sendResult = defaultRocketMQTemplate.getTemplate().syncSend(destination, Objects.requireNonNull(message, "Message cannot be null."));
        checkStatus(sendResult);
    }

    @SneakyThrows
    public void sendMessages(ServiceBusMessageBatch batch) {
        Objects.requireNonNull(batch, "Message cannot be null.");
        if (CollectionUtils.isEmpty(batch.getServiceBusMessageList())) {
            log.info("ServiceBusMessageBatch size 0.");
            return;
        }
        //split the large batch into small ones:
        List<Message> messageList = batch.getServiceBusMessageList().stream().map(b -> defaultRocketMQTemplate.doConvertPayLoadRocketMsg(destination, b, b.getHeaders())).collect(Collectors.toList());
        // 官方推荐批量发送注意消息不要超过x M
        ListSplitter splitter = new ListSplitter(messageList);
        while (splitter.hasNext()) {
            List<Message> listItem = splitter.next();
            SendResult sendResult = defaultRocketMQTemplate.getTemplate().getProducer().send(listItem);
            System.out.printf("%s", sendResult);
        }
    }

    private void checkStatus(SendResult sendResult) {
        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            log.debug("Message acknowledged from RocketMQ: Id = {}", sendResult.getMsgId());
        } else {
            log.error("Message send field from RocketMQ: Id = {}; status = {}", sendResult.getMsgId(), sendResult.getSendStatus());
        }
    }
}
