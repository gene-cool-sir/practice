package com.example.practicejavase.collection.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue是Java中的一个无界阻塞延迟队列实现类，它可以用来保存实现了Delayed接口的元素，这些元素按照指定的延迟时间进行排序。
 * 在DelayQueue中，只有延迟时间到了的元素才能被取出。
 *
 * DelayQueue适用于以下场景：
 *
 * 定时任务调度，可以将需要延迟执行的任务放入DelayQueue中；
 * 缓存系统中的过期元素自动删除；
 * 仿真系统中的事件调度，可以模拟事件的延迟发生。
 */
public class DelayQueueExample {
    // 自定义延迟元素类
    static class DelayedElement implements Delayed {
        private String element;
        private long delayTime;

        public DelayedElement(String element, long delayTime) {
            this.element = element;
            this.delayTime = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.delayTime, ((DelayedElement)o).delayTime);
        }

        public String getElement() {
            return element;
        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();

        // 添加延迟元素
        delayQueue.put(new DelayedElement("apple", 2000)); // 2秒延迟
        delayQueue.put(new DelayedElement("banana", 2000)); // 3秒延迟
        delayQueue.put(new DelayedElement("cherry", 1000)); // 1秒延迟

        // 消费延迟元素
        try {
            while (!delayQueue.isEmpty()) {
                DelayedElement delayedElement = delayQueue.take();
                System.out.println("Consumed: " + delayedElement.getElement());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
