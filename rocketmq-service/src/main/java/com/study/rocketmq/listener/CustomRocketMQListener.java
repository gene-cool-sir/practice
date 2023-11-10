package com.study.rocketmq.listener;

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

        // 指定消费者线程数，默认64，生产中请注意配置，避免过大或者过小
        consumeThreadMax = 5
)
public class CustomRocketMQListener implements RocketMQListener<ServiceBusMessage> {

    @Override
    public void onMessage(ServiceBusMessage message) {
        System.out.println("消费消息:" + message);
    }
}
