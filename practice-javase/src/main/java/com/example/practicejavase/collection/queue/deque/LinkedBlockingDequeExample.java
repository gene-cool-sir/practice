package com.example.practicejavase.collection.queue.deque;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * LinkedBlockingDeque是Java中的一个双端阻塞队列实现类，它是通过链表实现的，可以在队列的两端进行插入和删除操作。
 * LinkedBlockingDeque支持阻塞操作，即当队列满或空时，插入和删除操作会阻塞线程。
 *
 * LinkedBlockingDeque适用于以下场景：
 *
 * 需要在队列的两端进行高性能插入和删除操作的场景；
 * 需要支持线程之间的任务协作的场景，因为LinkedBlockingDeque支持阻塞操作。
 */
public class LinkedBlockingDequeExample {
    public static void main(String[] args) {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(3);

        // 生产者线程
        Thread producerThread = new Thread(() -> {
            try {
                // 在队列尾部插入元素
                deque.putLast("Element 1");
                deque.putLast("Element 2");
                deque.putLast("Element 3");
                System.out.println("Producer: 3 elements are produced.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 消费者线程
        Thread consumerThread = new Thread(() -> {
            try {
                // 从队列头部删除元素
                String element = deque.takeFirst();
                System.out.println("Consumer: element \"" + element + "\" is consumed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动生产者和消费者线程
        producerThread.start();
        consumerThread.start();
    }
}
