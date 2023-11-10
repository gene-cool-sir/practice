package com.study.rocketmq.template.consumer;

import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * 自定义RocketMQTemplate, 当指定定了相关属性,则会使用自定义属性,否则默认使用RocketMQTemplate的属性,
 * 此种方式可以完全自定义消费组中的消费者实例
 */
@ExtRocketMQTemplateConfiguration(group = "custom_group")
public class CustomConsumerRocketMQTemplate extends RocketMQTemplate {

}
