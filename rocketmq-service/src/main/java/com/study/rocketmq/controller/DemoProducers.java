package com.study.rocketmq.controller;

import com.study.rocketmq.domain.BaseMqMessage;
import com.study.rocketmq.domain.RocketMqBizConstant;
import com.study.rocketmq.servicebus.ServiceBusMessage;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoProducers {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/producer")
    public String producersMessage() {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("我发了很长一长串的信息,用来测试OK");
        serviceBusMessage.setTopic(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC);
        serviceBusMessage.setTags(RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG);
        rocketMQTemplate.convertAndSend(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC+":" + RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG, serviceBusMessage);
        return "OK";
    }
}
