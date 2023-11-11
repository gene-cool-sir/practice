package com.study.rocketmq.controller;

import com.study.rocketmq.domain.RocketMqBizConstant;
import com.study.rocketmq.servicebus.ServiceBusMessage;
import com.study.rocketmq.template.DefaultRocketMQTemplate;
import com.study.rocketmq.template.producer.CustomProducerRocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoProducersController {
    @Resource
    private DefaultRocketMQTemplate defaultRocketMQTemplate;

    @RequestMapping("/producer")
    public String producersMessage() {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("我发了很长一长串的信息,用来测试OK");
        serviceBusMessage.setTopic(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC);
        serviceBusMessage.setTags(RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG);
        defaultRocketMQTemplate.convertAndSend(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC+":" + RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG, serviceBusMessage);
        return "OK";
    }

    @RequestMapping("/producer1")
    public String producersMessage1() {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("我发了很长一长串的信息,用来测试OK,headers");
        serviceBusMessage.setTopic(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC);
        serviceBusMessage.setTags(RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG);
        defaultRocketMQTemplate.convertAndSend(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC+":" + RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG,
                serviceBusMessage,serviceBusMessage.getHeaders());
        return "OK";
    }

    @RequestMapping("/producer2")
    public String producersTransactionMessage() {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("我发了事务消息,确保本地事务处理成功,否则需要过一段时间检查是否发送成功进行再次投递,用来测试OK,headers");
        serviceBusMessage.setTopic(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC);
        serviceBusMessage.setTags(RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG);
        defaultRocketMQTemplate.sendMessageInTransaction(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC+":" + RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG,
                serviceBusMessage,
                serviceBusMessage.getHeaders(),
                "我作为参数传递给监听者");
        return "OK";
    }
}
