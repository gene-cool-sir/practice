package com.example.practicejavase.collection.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList是Java中的线程安全的ArrayList的一个实现类，它通过对底层数组的复制来实现线程安全性。
 * 在CopyOnWriteArrayList中，当有线程修改集合中的元素时，不会直接修改原来的数组，而是将原来的数组复制一份，在复制的数组上进行修改，
 * 修改完成后再将原来的数组引用替换为新的数组。
 *
 * CopyOnWriteArrayList适用于以下场景：
 *
 * 对读操作频繁，写操作较少的场景；
 * 当确保一个集合不会被其他线程修改时，可以避免对该集合进行同步操作。
 */
public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // 添加元素
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        // 遍历集合
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }

        // 修改集合
        list.add("date");

        // 再次遍历集合
        Iterator<String> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            String element = iterator2.next();
            System.out.println(element);
        }
    }
}
