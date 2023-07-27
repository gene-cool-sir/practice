package com.example.practicejavase.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue是Java中的一个有界阻塞队列实现类，它可以在队列满时阻塞生产者线程，
 * 当队列空时阻塞消费者线程。它的底层实现使用了一个数组来保存元素，因此在创建ArrayBlockingQueue时需要指定队列的容量。
 *
 * ArrayBlockingQueue适用于以下场景：
 *
 * 生产者和消费者之间的任务处理速度不一致，可以通过阻塞操作来平衡二者之间的速度差异；
 * 需要存储固定数量的元素，并且对元素的顺序有要求；
 * 需要保证线程安全、有界队列的场景。
 */
public class ArrayBlockingQueueExample {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

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
