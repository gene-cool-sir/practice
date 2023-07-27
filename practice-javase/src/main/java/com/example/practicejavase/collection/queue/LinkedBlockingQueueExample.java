package com.example.practicejavase.collection.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue是Java中的一个可选有界的阻塞队列实现类，它可以在队列满时阻塞生产者线程，当队列空时阻塞消费者线程。
 * 它的底层实现使用了一个链表来保存元素，并且支持可选的固定容量。
 *
 * LinkedBlockingQueue适用于以下场景：
 *
 * 生产者和消费者之间的任务处理速度不一致，可以通过阻塞操作来平衡二者之间的速度差异；
 * 需要存储大量元素的场景，因为它的底层是一个链表，没有固定容量限制；
 * 需要保证线程安全、可选有界队列的场景。
 */
public class LinkedBlockingQueueExample {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // 生产者线程
        Thread producerThread = new Thread(() -> {
            try {
                // 生产3个元素
                queue.put("apple");
                queue.put("banana");
                queue.put("cherry");
                System.out.println("Producer: 3 elements are produced.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 消费者线程
        Thread consumerThread = new Thread(() -> {
            try {
                // 消费3个元素
                for (int i = 0; i < 3; i++) {
                    String element = queue.take();
                    System.out.println("Consumer: element \"" + element + "\" is consumed.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动生产者和消费者线程
        producerThread.start();
        consumerThread.start();
    }
}
