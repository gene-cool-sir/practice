package com.study.rocketmq.template.producer;

import com.study.rocketmq.template.DefaultRocketMQTemplate;
import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * 自定义不同生产者组的方式创建RocketMQTemplate, 当指定定了相关属性,则会使用自定义属性,否则默认使用RocketMQTemplate的属性,
 * 此种方式可以完全自定义生产组中的生产实例属性相关信息
 * @ExtRocketMQTemplateConfiguration 注解生产的Producer对象为DefaultMQProducer,不使用这个注解生成的Bean对象producer为null
 */
@ExtRocketMQTemplateConfiguration(group = "producer_group")
public class CustomProducerRocketMQTemplate extends RocketMQTemplate  {

}
