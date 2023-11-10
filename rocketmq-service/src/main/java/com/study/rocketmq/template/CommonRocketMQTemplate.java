package com.study.rocketmq.template;

import com.alibaba.fastjson.JSONObject;
import com.study.rocketmq.domain.BaseMqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by gene
 * 自定义的RocketMQTemplate,一些通用方法
 */
@Component
@Slf4j
public class CommonRocketMQTemplate extends RocketMQTemplate {

    /**
     * 分隔符
     */
    public static final String DELIMITER = ":";

    /**
     * 构建目的地
     */
    public String buildDestination(String topic, String tag) {
        return topic + DELIMITER + tag;
    }

    /**
     * 发送同步消息
     */
    public <T extends BaseMqMessage> SendResult send(String topic, String tag, T message) {
        return send(buildDestination(topic,tag), message);
    }

    public <T extends BaseMqMessage> SendResult send(String destination, T message) {
        // 设置业务键，此处根据公共的参数进行处理
        Message<T> sendMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, message.getKey()).build();
        SendResult sendResult = this.syncSend(destination, sendMessage);
        log.info("[{}]同步消息[{}]发送结果[{}]",
                destination,
                JSONObject.toJSONString(message),
                JSONObject.toJSONString(sendResult));
        return sendResult;
    }

    public <T extends BaseMqMessage> SendResult send(String destination, T message, MessageHeaders messageHeaders) {

        Message<T> sendMessage = MessageBuilder.createMessage(message, messageHeaders);
        SendResult sendResult = this.syncSend(destination, sendMessage);
        log.info("[{}]同步消息[{}]发送结果[{}]",
                destination,
                JSONObject.toJSONString(message),
                JSONObject.toJSONString(sendResult));
        return sendResult;
    }

    /**
     * 发送延迟消息
     */
    public <T extends BaseMqMessage> SendResult send(String topic, String tag, T message, int delayLevel) {
        return send(buildDestination(topic,tag), message, delayLevel);
    }

    public <T extends BaseMqMessage> SendResult send(String destination, T message, int delayLevel) {
        Message<T> sendMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, message.getKey()).build();
        SendResult sendResult = this.syncSend(destination, sendMessage, 3000, delayLevel);
        log.info("[{}]延迟等级[{}]消息[{}]发送结果[{}]",
                destination,
                delayLevel,
                JSONObject.toJSONString(message),
                JSONObject.toJSONString(sendResult));
        return sendResult;
    }
}
