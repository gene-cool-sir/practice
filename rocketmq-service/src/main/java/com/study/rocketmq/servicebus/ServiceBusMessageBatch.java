package com.study.rocketmq.servicebus;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gene
 */
@Getter
@Setter
public class ServiceBusMessageBatch {

    private final List<ServiceBusMessage> serviceBusMessageList;

    ServiceBusMessageBatch() {
        this.serviceBusMessageList = new ArrayList<>();
    }
    public boolean tryAddMessage(ServiceBusMessage serviceBusMessage) {
        if (serviceBusMessage == null) {
            return false;
        } else {
            this.serviceBusMessageList.add(serviceBusMessage);
            return true;
        }
    }
}
