package com.example.practicejavase.collection.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue是Java中的一个支持优先级的阻塞队列实现类，它可以根据元素的优先级进行排序。
 * 在PriorityBlockingQueue中，插入操作和删除操作都可以在常数时间内完成，即O(1)时间复杂度。
 *
 * PriorityBlockingQueue适用于以下场景：
 *
 * 需要按照优先级处理任务的场景，比如任务调度系统，任务如果具有不同的优先级，可以使用PriorityBlockingQueue来存储任务，并按照优先级进行调度；
 * 需要按照一定规则对元素进行排序的场景。
 */
public class PriorityBlockingQueueExample {
    public static void main(String[] args) {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        // 生产者线程
        Thread producerThread = new Thread(() -> {
            // 生产3个具有不同优先级的任务
            Task task1 = new Task("Task 1", 2);
            Task task2 = new Task("Task 2", 1);
            Task task3 = new Task("Task 3", 3);
            queue.put(task1);
            queue.put(task2);
            queue.put(task3);
            System.out.println("Producer: 3 tasks are produced.");
        });

        // 消费者线程
        Thread consumerThread = new Thread(() -> {
            try {
                // 消费3个任务
                for (int i = 0; i < 3; i++) {
                    Task task = queue.take();
                    System.out.println("Consumer: task \"" + task.name + "\" with priority " + task.priority + " is consumed.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动生产者和消费者线程
        producerThread.start();
        consumerThread.start();
    }

    // 任务类，包含任务名称和优先级
    static class Task implements Comparable<Task> {
        String name;
        int priority;

        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Task o) {
            return Integer.compare(this.priority, o.priority);
        }
    }
}
