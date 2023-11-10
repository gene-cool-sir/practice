package com.study.rocketmq.template.producer;

import com.study.rocketmq.template.CommonRocketMQTemplate;
import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * 自定义RocketMQTemplate, 当指定定了相关属性,则会使用自定义属性,否则默认使用RocketMQTemplate的属性,
 * 此种方式可以完全自定义生产组中的生产实例属性相关信息
 */
@ExtRocketMQTemplateConfiguration(group = "producer_group")
public class CustomProducerRocketMQTemplate extends CommonRocketMQTemplate {

}
