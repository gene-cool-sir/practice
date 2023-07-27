package com.example.practicejavase.collection.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue是Java中的一个无缓冲的阻塞队列实现类，它支持无缓冲的、一对一的线程通信。
 * 在SynchronousQueue中，每个插入操作必须等待对应的删除操作，反之亦然。
 *
 * SynchronousQueue适用于以下场景：
 *
 * 数据交换场景，比如一个线程产生数据，而另一个线程消费数据，需要在两个线程之间同步数据时可以使用SynchronousQueue；
 * 线程池中的工作队列，适用于需要等待工作线程处理完任务的场景
 */
public class SynchronousQueueExample {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        // 生产者线程
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    String element = "Element " + i;
                    // 插入元素，必须等待消费者线程来消费
                    queue.put(element);
                    System.out.println("Producer: element \"" + element + "\" is produced.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 消费者线程
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    // 获取元素，必须等待生产者线程来产生
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
