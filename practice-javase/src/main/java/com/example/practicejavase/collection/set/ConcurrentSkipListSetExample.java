package com.example.practicejavase.collection.set;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * ConcurrentSkipListSet是Java中的一个并发有序Set实现，它使用跳表（skip list）数据结构来保持元素的有序性，并且支持高并发的插入、删除和查找操作。
 */
public class ConcurrentSkipListSetExample {
    public static void main(String[] args) {
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();

        // 并发插入数据
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                set.add(i);
            }
        }).start();

        // 并发删除数据
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                set.remove(i);
            }
        }).start();

        // 并发查找数据
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(set.contains(i));
            }
        }).start();
    }


}
