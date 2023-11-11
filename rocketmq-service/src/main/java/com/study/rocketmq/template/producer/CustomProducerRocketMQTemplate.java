package com.study.rocketmq.template.producer;

import com.study.rocketmq.template.BaseRocketMQTemplate;
import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;

/**
 * 自定义不同生产者组的方式创建RocketMQTemplate, 当指定定了相关属性,则会使用自定义属性,否则默认使用RocketMQTemplate的属性,
 * 此种方式可以完全自定义生产组中的生产实例属性相关信息
 * @ExtRocketMQTemplateConfiguration 注解生产的Producer对象为DefaultMQProducer
 */
@ExtRocketMQTemplateConfiguration(group = "producer_group")
public class CustomProducerRocketMQTemplate extends BaseRocketMQTemplate  /*implements CommandLineRunner*/ {



   /* @Override
    public void run(String... args) throws Exception {
        // 重新构建事务消息对象
        DefaultMQProducer producer = this.getProducer();
        producer.shutdown();
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer(producer.getProducerGroup());
        BeanUtils.copyProperties(producer,transactionMQProducer);
        try {
            transactionMQProducer.start();
        } catch (MQClientException e) {
            throw new BeanDefinitionValidationException(String.format("Failed to startup MQProducer for RocketMQTemplate {}",
                    producer.getInstanceName()), e);
        }
    }*/

}
