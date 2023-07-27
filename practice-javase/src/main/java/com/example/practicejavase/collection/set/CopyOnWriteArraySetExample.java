package com.example.practicejavase.collection.set;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArraySet是Java中的一个并发集合，它基于Copy-On-Write（写时复制）技术实现，提供了线程安全的访问和修改操作。
 * CopyOnWriteArraySet底层使用数组来存储元素，并且通过在修改时创建新的数组来保证线程安全
 */
public class CopyOnWriteArraySetExample {
    private static CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

    /**
     * 高并发的读多写少场景：CopyOnWriteArraySet适用于多线程环境下有大量的读操作和很少的写操作的场景，
     * 因为它的写操作可能会较慢（由于创建新数组），但读操作不会阻塞。这种场景下，
     * @param args
     */
    public static void main(String[] args) {
        // 并发添加数据
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                set.add("Element " + i);
            }
        }).start();

        // 并发读取数据
        new Thread(() -> {
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }).start();


        distinct();
    }


    /**
     * 去重操作：CopyOnWriteArraySet是一个无重复元素的集合，适用于需要对元素进行去重操作的场景。
     */
    public static void distinct() {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(3);
        set.add(4);

        System.out.println(set); // [1, 2, 3, 4]
    }
}
