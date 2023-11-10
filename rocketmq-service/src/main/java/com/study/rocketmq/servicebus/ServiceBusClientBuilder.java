/*
package com.study.rocketmq.servicebus;

*/
/**
 * Created by gene
 *//*

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
        private String queueName;
        private String topicName;

        private ServiceBusSenderClientBuilder() {
        }

        public ServiceBusSenderClientBuilder queueName(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public ServiceBusSenderClientBuilder topicName(String topicName) {
            this.topicName = topicName;
            return this;
        }


        public ServiceBusSenderAsyncClient buildAsyncClient() {

            return new ServiceBusSenderAsyncClient(queueName);
        }

        public ServiceBusSenderClient buildClient() {
            return new ServiceBusSenderClient(queueName);
        }
    }

}
*/
