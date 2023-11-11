package com.study.rocketmq.listener;

import com.study.rocketmq.domain.RocketMqBizConstant;
import com.study.rocketmq.servicebus.ServiceBusMessage;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC,
        consumerGroup = RocketMqBizConstant.SERVICE_BUS_TEST_CONSUME_GROUP)
public class DefaultConsumerMessageListener implements RocketMQListener<ServiceBusMessage> {
    @Override
    public void onMessage(ServiceBusMessage message) {
        System.out.println("开始消费数据");
    }
}
