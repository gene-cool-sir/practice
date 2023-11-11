package com.study.rocketmq.template;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by gene
 */
@Component
public class DefaultRocketMQTemplate extends BaseRocketMQTemplate {

   /* private RocketMQTemplate rocketMQTemplate;

    @Resource
    public void setRocketMQTemplate(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
        setTemplate(rocketMQTemplate);
    }*/
}
