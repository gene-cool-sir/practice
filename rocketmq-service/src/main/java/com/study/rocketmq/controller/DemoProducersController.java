package com.study.rocketmq.controller;

import com.study.rocketmq.domain.RocketMqBizConstant;
import com.study.rocketmq.servicebus.ServiceBusClientBuilder;
import com.study.rocketmq.servicebus.ServiceBusMessage;
import com.study.rocketmq.servicebus.ServiceBusSenderAsyncClient;
import com.study.rocketmq.servicebus.ServiceBusSenderClient;
import com.study.rocketmq.template.DefaultRocketMQTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoProducersController {
    @Resource
    private DefaultRocketMQTemplate defaultRocketMQTemplate;

    private ServiceBusSenderAsyncClient dabiWorkoutQueueClient;

    private ServiceBusSenderClient dabiUserQueueClient;



    @RequestMapping("/producer")
    public String producersMessage() {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("我发了很长一长串的信息,用来测试OK，并通过TAG普通消费消息");
        serviceBusMessage.setTopic(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC);
        serviceBusMessage.setTags(RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG);
        defaultRocketMQTemplate.convertAndSend(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC+":" + RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG, serviceBusMessage);
        return "OK";
    }

    @RequestMapping("/producer1")
    public String producersMessage1() {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("我发了很长一长串的信息,用来测试OK,并记录消费行为");
        serviceBusMessage.setTopic(RocketMqBizConstant.SERVICE_BUS_TEST_TOPIC);
        serviceBusMessage.setTags(RocketMqBizConstant.SERVICE_BUS_CONSUME_TAG_BASE_MESSAGE);
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

    // 异步
    @RequestMapping("/producer3")
    public String producersByServiceBusMessageAsync() {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("我尝试通过topic构建业务Client，进行发送异步消息");
        serviceBusMessage.setTopic("test_service_bus_async_topic");
        serviceBusMessage.setTags("");
        if (dabiWorkoutQueueClient == null) {
            dabiWorkoutQueueClient = new ServiceBusClientBuilder()
                    .connectionString("")
                    .sender()
                    .queueName("test_group_topic")
                    .buildAsyncClient();
        }

        dabiWorkoutQueueClient.sendMessage(serviceBusMessage);
        return "ok";

    }

    @RequestMapping("/producer4")
    public String producersByServiceBusMessageSync() {
        ServiceBusMessage serviceBusMessage = new ServiceBusMessage("我尝试通过topic构建业务Client，进行发送同步消息");
        serviceBusMessage.setTopic("test_service_bus_sync_topic");
        serviceBusMessage.setTags("");
        if (dabiUserQueueClient == null) {
            dabiUserQueueClient = new ServiceBusClientBuilder()
                    .connectionString("")
                    .sender()
                    .queueName(serviceBusMessage.getTopic())
                    .buildClient();
        }

        dabiUserQueueClient.sendMessage(serviceBusMessage);
        return "ok";

    }
}
