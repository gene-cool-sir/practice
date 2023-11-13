package com.study.rocketmq.domain;

/**
 * RocketMQ事件组常量
 *
 *
 * @author gene
 */
public class RocketMqBizConstant {
    /**
     * 先在服务器创建好TOPIC,RocketMQ不建议开启自动创建Topic
     */
    public static final String SERVICE_BUS_TEST_TOPIC = "service_bus_test_topic";

    /**
     * 普通消费组
     */
    public static final String SERVICE_BUS_TEST_CONSUME_GROUP = "service_bus_test_consume_group";

    /**
     * 记录一些通用操作的消费组
     */
    public static final String SERVICE_BUS_TEST_CONSUME_GROUP_BASE_MESSAGE = "service_bus_test_consume_group_base_message";

    /**
     * topic分类标签TAG
     */
    public static final String SERVICE_BUS_CONSUME_TAG = "TAG1";
    public static final String SERVICE_BUS_CONSUME_TAG_BASE_MESSAGE = "TAG2";



    /**
     * 分隔符
     */
    public static final String DELIMITER = ":";

    /**
     * 构建目的地
     */
    public static String buildDestination(String topic, String tag) {
        return topic + DELIMITER + tag;
    }

}
