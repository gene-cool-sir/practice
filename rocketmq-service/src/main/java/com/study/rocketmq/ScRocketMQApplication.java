package com.study.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

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
}
