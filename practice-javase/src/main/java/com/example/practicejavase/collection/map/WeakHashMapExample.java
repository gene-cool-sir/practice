package com.example.practicejavase.collection.map;

import java.util.*;

/**
 * WeakHashMap 是 Java 中的一种特殊的 Map 实现，它是基于弱引用的键存储数据的。与普通的 HashMap 不同，WeakHashMap 允许在没有对键的强引用存在时，自动将键从 Map 中移除，以便垃圾回收器回收。
 *
 * WeakHashMap 的主要特点如下：
 *
 * 弱引用键：WeakHashMap 使用弱引用作为键，这意味着当没有对键的强引用存在时，垃圾回收器有权将键从 WeakHashMap 中移除。当一个键不再被使用，且没有其他强引用指向它时，垃圾回收器将会自动移除该键对应的键值对。
 *
 * 自动清理：WeakHashMap 会监视键的引用状态，并在垃圾回收器回收键时自动清理相应的键值对。这使得 WeakHashMap 特别适用于缓存、临时映射和一次性映射等场景，因为它确保只在需要时保留对键的引用。
 *
 * 内存管理：由于 WeakHashMap 会在适当的时机自动清理键值对，因此它可以很好地帮助我们管理内存，避免因为过度缓存或映射而导致的内存泄漏问题。
 *
 * 需要注意的是，由于 WeakHashMap 使用弱引用作为键，因此键对象需要满足以下条件：
 *
 * 键不能为 null。
 * 键必须是可以被垃圾回收的对象，即没有其他强引用指向它。
 */
public class WeakHashMapExample {
    public static void main(String[] args) {
        WeakHashMap<Key, String> weakHashMap = new WeakHashMap<>();

        Key key1 = new Key(1);
        Key key2 = new Key(2);

        weakHashMap.put(key1, "Value 1");
        weakHashMap.put(key2, "Value 2");

        System.out.println(weakHashMap.get(key1)); // Value 1
        System.out.println(weakHashMap.get(key2)); // Value 2

        key1 = null; // 使 key1 的强引用失效

        System.gc(); // 显式触发垃圾回收

        System.out.println(weakHashMap.get(key1)); // null
        System.out.println(weakHashMap.get(key2)); // Value 2

        /**
         * 在示例代码中，我们创建了一个 WeakHashMap 对象 weakHashMap，并向其中添加了两个键值对，其中键分别为 Key 对象 key1 和 key2。
         * 在获取键对应的值时，我们可以看到正确地返回了对应的值。
         *
         * 之后，我们将 key1 的强引用设为 null，并显式触发垃圾回收。此时再次获取 key1 和 key2 对应的值时，可以看到 key1 对应的值为 null，
         * 而 key2 仍然存在且返回了正确的值。这表明由于 key1 失去了强引用，垃圾回收器将其自动清理了。
         */
    }

    static class Key {
        private final int id;

        public Key(int id) {
            this.id = id;
        }
    }
}
