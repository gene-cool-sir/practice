package com.study.rocketmq.servicebus;


import com.study.rocketmq.domain.RocketMqBizConstant;

/**
 * Created by gene
 */
public class ServiceBusClientBuilder {

    public ServiceBusClientBuilder() {

    }

    public ServiceBusClientBuilder connectionString(String connectionString) {

        return this;
    }

    public ServiceBusSenderClientBuilder sender() {
        return new ServiceBusSenderClientBuilder();
    }

    public final class ServiceBusSenderClientBuilder {

        private String tag;

        private String topic;

        private String destination;

        private ServiceBusSenderClientBuilder() {
        }

        public ServiceBusSenderClientBuilder queueName(String destination) {
            this.destination = destination;
            return this;
        }

        public ServiceBusSenderClientBuilder destination(String topic, String tag) {
            this.destination = RocketMqBizConstant.buildDestination(topic, tag);
            return this;
        }

        public ServiceBusSenderClientBuilder topicName(String topic, String tag) {
            this.topic = topic;
            this.tag = tag;
            this.destination = RocketMqBizConstant.buildDestination(topic, tag);

            return this;
        }


        public ServiceBusSenderAsyncClient buildAsyncClient() {

            return new ServiceBusSenderAsyncClient(destination);

        }

        public ServiceBusSenderClient buildClient() {
            return new ServiceBusSenderClient(destination);
        }
    }

}
