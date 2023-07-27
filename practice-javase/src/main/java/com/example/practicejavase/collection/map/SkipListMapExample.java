package com.example.practicejavase.collection.map;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap是Java并发集合库中的一种有序映射表，它是基于跳跃表（SkipList）数据结构实现的，具备高效的查找、插入和删除操作。
 *
 * ConcurrentSkipListMap的作用是提供一个线程安全的有序映射表，在多线程环境下可以高效地进行操作，而不需要对整个数据结构进行互斥访问锁。
 *
 * 使用场景：
 * 1. 多线程环境下需要高效地进行有序映射操作。ConcurrentSkipListMap是线程安全的，
 * 可以在多线程环境下并发地进行操作，而不需要同步互斥锁，因此适合在多线程环境中使用。
 * 2. 需要在有序的键值对映射中进行高效查找、插入和删除操作。ConcurrentSkipListMap内部使用跳跃表数据结构，
 * 可以在O(log n)的时间复杂度内完成这些操作，因此适合对有序映射进行频繁的增删改查操作。
 * 3. 需要进行范围查找操作。ConcurrentSkipListMap提供了一些方法可以进行范围查找，如subMap、headMap和tailMap等，
 * 可以根据指定的键范围来获取子映射，适合进行范围查找操作。
 * 4. 需要同时兼顾并发性能和空间效率。ConcurrentSkipListMap在多线程环境下可以保证操作的线程安全性，
 * 并且在大多数情况下可以比较好地平衡读写性能和内存占用，非常适合在需要兼顾性能和空间效率的场景中使用。
 */
public class SkipListMapExample {

    public static void main(String[] args) {
        // 创建一个ConcurrentSkipListMap对象
        ConcurrentSkipListMap<Integer, String> skipListMap = new ConcurrentSkipListMap<>();

        // 添加键值对
        skipListMap.put(1, "Apple");
        skipListMap.put(3, "Banana");
        skipListMap.put(2, "Orange");

        // 获取大小
        System.out.println("Size: " + skipListMap.size());

        // 获取指定键对应的值
        System.out.println("Value for key 2: " + skipListMap.get(2));// Orange

        // 删除键值对
        skipListMap.remove(3);

        // 遍历并打印所有键值对
        for (Integer key : skipListMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + skipListMap.get(key));
        }
    }
}
