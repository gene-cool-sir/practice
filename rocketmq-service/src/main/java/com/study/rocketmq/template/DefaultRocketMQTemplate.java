package com.study.rocketmq.template;

import com.alibaba.fastjson.JSONObject;
import com.study.rocketmq.domain.BaseMqMessage;
import com.study.rocketmq.domain.RocketMqDelayLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gene
 * 自定义的包装了一下RocketMQTemplate的部分方法；
 */
@Slf4j
@Component
public class DefaultRocketMQTemplate extends AbstractMessageSendingTemplate<String> {

    /**
     * 原生默认的RocketMQTemplate
     */
    @Resource(name = "rocketMQTemplate")
    private RocketMQTemplate template;

    public RocketMQTemplate getTemplate() {
        return template;
    }

    @Override
    protected void doSend(String destination, Message<?> message) {
        SendResult sendResult = template.syncSend(destination, message);
        if (log.isDebugEnabled()) {
            log.debug("send message to `{}` finished. result:{}", destination, sendResult);
        }
    }

    /**
     * 发送同步消息
     */
    public <T extends BaseMqMessage> SendResult syncSend(String destination,
                                                     Object payload,
                                                     Map<String, Object> headers) {
        Message<?> sendMessage = this.doConvertSpringMsg(payload, headers, null);
        SendResult sendResult = template.syncSend(destination, sendMessage);
        log.info("[{}]同步消息[{}]发送结果[{}]",
                destination,
                JSONObject.toJSONString(payload),
                JSONObject.toJSONString(sendResult));
        return sendResult;
    }

    public <T extends BaseMqMessage> SendResult syncSend(String destination,
                                                     T message,
                                                     MessageHeaders messageHeaders) {

        Message<T> sendMessage = MessageBuilder.createMessage(message, messageHeaders);
        SendResult sendResult = template.syncSend(destination, sendMessage);
        log.info("[{}]同步消息[{}]发送结果[{}]",
                destination,
                JSONObject.toJSONString(message),
                JSONObject.toJSONString(sendResult));
        return sendResult;
    }

    /**
     * 发送延迟消息
     */
    public <T extends BaseMqMessage> SendResult sendByDelayLevel(final String destination,
                                                                 Object payload,
                                                                 Map<String, Object> headers,
                                                                 int delayLevel) {
        Message<?> msg = this.doConvertSpringMsg(payload, headers, null);
        return sendByDelayLevel(destination, msg, delayLevel);
    }

    public <T extends BaseMqMessage> SendResult sendByDelayLevel(String destination, Message<?> msg, int delayLevel) {
        Arrays.stream(RocketMqDelayLevel.values()).filter(l -> l.getDelayLevel() == delayLevel).findAny()
                .orElseThrow(() -> new MessageDeliveryException("Delay levels do not match"));
        SendResult sendResult = template.syncSend(destination, msg, template.getProducer().getSendMsgTimeout(), delayLevel);
        log.info("[{}]延迟等级[{}]消息[{}]发送结果[{}]",
                destination,
                delayLevel,
                JSONObject.toJSONString(msg),
                JSONObject.toJSONString(sendResult));
        return sendResult;
    }

    /**
     * Send Spring Message in Transaction
     * @return TransactionSendResult
     * @throws MessagingException
     */
    public TransactionSendResult sendMessageInTransaction(final String destination,
                                                          Object payload,
                                                          @Nullable Map<String, Object> headers,
                                                          final Object arg) throws MessagingException {
        Message<?> msg = this.doConvertSpringMsg(payload, headers, null);
        return template.sendMessageInTransaction(destination, msg, arg);
    }

    /**
     * MQ Message对象转 Spring Message对象
     * @param payload 传输数据
     * @param headers
     * @param postProcessor
     * @see org.springframework.messaging.Message
     */
    public Message<?> doConvertSpringMsg(Object payload, Map<String, Object> headers,
                                   MessagePostProcessor postProcessor) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        return super.doConvert(payload, headers, postProcessor);
    }

    /**
     * Spring Message对象转 MQ Message对象
     * @param destination
     * @param message
     * @see org.apache.rocketmq.common.message.Message
     * @return
     */
    public org.apache.rocketmq.common.message.Message doConvertRocketMsg(String destination, Message<?> message) {
        Message<?> msg = this.doConvert(message.getPayload(), message.getHeaders(), null);
        return RocketMQUtil.convertToRocketMessage(template.getMessageConverter(), StandardCharsets.UTF_8.name(),
                destination, msg);
    }

    public org.apache.rocketmq.common.message.Message doConvertPayLoadRocketMsg(String destination,
                                                                                Object payload,
                                                                                Map<String, Object> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        Message<?> msg = this.doConvert(payload, headers, null);
        return RocketMQUtil.convertToRocketMessage(template.getMessageConverter(), StandardCharsets.UTF_8.name(),
                destination, msg);
    }

    protected Message<?> doConvert(Object payload, Map<String, Object> headers, MessagePostProcessor postProcessor) {
        Message<?> message = super.doConvert(payload, headers, postProcessor);
        MessageBuilder<?> builder = MessageBuilder.fromMessage(message);
        builder.setHeaderIfAbsent(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN);
        return builder.build();
    }

}
