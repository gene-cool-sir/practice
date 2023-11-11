package com.study.rocketmq.servicebus;

import com.study.rocketmq.domain.BaseMqMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by gene
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceBusMessage extends BaseMqMessage<String> implements Serializable{
    private static final long serialVersionUID = 1L;
    private String content;
    private String contentType;
    private String subject;
    private String messageId;
    private String sessionId;
    private String partitionKey;

    public ServiceBusMessage(byte[] body) {
        content = Arrays.toString(Objects.requireNonNull(body, "'body' cannot be null."));
    }

    public ServiceBusMessage(String body) {
        content = Objects.requireNonNull(body, "'body' cannot be null.");
    }

    @Override
    public Map<String,Object> getHeaders() {
         return super.getHeaders();
    }
}
