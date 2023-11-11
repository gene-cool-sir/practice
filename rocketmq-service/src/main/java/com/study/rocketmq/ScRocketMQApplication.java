package com.study.rocketmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import java.util.List;

/**
 * @author ：gene
 * @description:
 **/
@SpringBootApplication
public class ScRocketMQApplication {
    //@Resource
    //private RocketMQTemplate rocketMQTemplate;
    //@Resource
   // private CustomRocketMQTemplate customRocketMQTemplate;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ScRocketMQApplication.class, args);
        Object customRocketMQTemplate1 = applicationContext.getBean("customProducerRocketMQTemplate");
        Object customRocketMQTemplate2 = applicationContext.getBean("rocketMQTemplate");
        System.out.println("启动成功...");

    }

   /* @Bean
    @Primary
    public RocketMQMessageConverter createRocketMQMessageConverter() {
        RocketMQMessageConverter converter = new RocketMQMessageConverter();
        CompositeMessageConverter compositeMessageConverter = (CompositeMessageConverter) converter.getMessageConverter();
        List<MessageConverter> messageConverterList = compositeMessageConverter.getConverters();
        for (MessageConverter messageConverter : messageConverterList) {
            if (messageConverter instanceof MappingJackson2MessageConverter) {
                MappingJackson2MessageConverter jackson2MessageConverter = (MappingJackson2MessageConverter) messageConverter;
                ObjectMapper objectMapper = jackson2MessageConverter.getObjectMapper();
                // 增加Java8时间模块支持，实体类可以传递LocalDate/LocalDateTime
                objectMapper.registerModules(new JavaTimeModule());
            }
        }
        return converter;
    }*/
}
