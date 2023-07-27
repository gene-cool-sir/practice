package com.example.practicejavase.collection.map;

import java.util.*;
import java.util.concurrent.*;

/**
 * ConcurrentHashMap 的主要特点如下：
 *
 * 线程安全：ConcurrentHashMap 使用了一种叫做分段锁（Segment）的机制，将整个 Map 分成多个段，每个段都可以独立地加锁，
 * 不同的线程可以同时访问不同的段，从而提高并发访问的效率。这使得 ConcurrentHashMap 可以在多线程环境下进行高效并发访问，而无需额外的同步措施。
 *
 * 高性能：由于使用了分段锁的机制，ConcurrentHashMap 具有较好的并发性能。当多个线程同时访问不同的段时，它们不会相互阻塞，从而提高了效率。
 *
 * 允许并发更新：与普通的 HashMap 不同，ConcurrentHashMap 允许在迭代时对其进行修改，而不会抛出 ConcurrentModificationException 异常。
 * 这是因为 ConcurrentHashMap 使用了一种叫做 "fail-safe" 的迭代器，在遍历时复制了一份 Map 的副本来进行迭代，从而避免了并发修改导致的异常。
 *
 * 需要注意的是，ConcurrentHashMap 并不保证迭代时的顺序。如果需要有序性，可以使用 LinkedHashMap 或 TreeMap。
 */
public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        concurrentHashMap.put("one", 1);
        concurrentHashMap.put("two", 2);
        concurrentHashMap.put("three", 3);

        for (Map.Entry<String, Integer> entry : concurrentHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
