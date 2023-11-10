package com.study.rocketmq.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 基础消息实体，包含基础的消息, 根据自己的业务消息设置更多的字段
 *
 * @author Created by gene
 */
@Data
public abstract class BaseMqMessage<T> implements Serializable {
    /**
     * 业务键，用于RocketMQ控制台查看消费情况
     */
    private String key;

    /**
     * topic
     */
    private String topic;

    /**
     * 标签
     */
    private String tags;

    /**
     * 发送消息来源
     */
    private String source = "";

    /**
     * 发送时间
     */
    private LocalDateTime sendTime = LocalDateTime.now();

    /**
     * 跟踪id，用于slf4j等日志记录跟踪id，方便查询业务链
     */
    private String traceId = UUID.randomUUID().toString();

    /**
     * 预设值一些属性
     * @return
     */

    public MessageHeaders getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        if (StringUtils.isNotBlank(key)) {
            headers.put(RocketMQUtil.toRocketHeaderKey(RocketMQHeaders.KEYS), key);
        }
        if (StringUtils.isNotBlank(topic)) {
            headers.put(RocketMQUtil.toRocketHeaderKey(RocketMQHeaders.TOPIC), topic);
        }
        if (StringUtils.isNotBlank(tags)) {
            headers.put(RocketMQUtil.toRocketHeaderKey(RocketMQHeaders.TAGS), tags);
        }
        return new MessageHeaders(headers);
    }

}
