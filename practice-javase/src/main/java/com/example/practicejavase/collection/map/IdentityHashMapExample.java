package com.example.practicejavase.collection.map;

import java.util.*;

/**
 * IdentityHashMap 的主要特点如下：
 *
 * 引用相等性：IdentityHashMap 使用引用相等性来判断键的相等性。即只有当两个键的引用指向同一个对象时，
 * 它们才被认为是相等的。这与其他 Map 实现不同，其他 Map 实现使用 equals 方法来判断键的相等性。
 *
 * 不依赖 hashCode 和 equals：由于 IdentityHashMap 不使用 hashCode 和 equals 方法，
 * 因此键的 hashCode 和 equals 方法对它没有影响。这意味着即使键的 hashCode 和 equals 方法被重写，IdentityHashMap 仍然可以正确工作。
 *
 * 适用于需要引用相等性的场景：由于 IdentityHashMap 使用对象的引用相等性来判断键的相等性，
 * 它特别适用于需要考虑对象引用相等性的场景。例如，在使用对象作为键的映射表时，如果需要确保两个引用相同的键也能被识别为相等，则可以使用 IdentityHashMap。
 *
 * 需要注意的是，由于 IdentityHashMap 不使用 equals 方法，因此它的性能可能较其他 Map 实现略高。
 * 但需要注意它可能会占用较多的内存，因为它不会对键进行任何哈希码计算或者相等性比较。
 */
public class IdentityHashMapExample {
    public static void main(String[] args) {
        IdentityHashMap<String, Integer> identityHashMap = new IdentityHashMap<>();

        String key1 = new String("key");
        String key2 = new String("key");
        //String key2 = key1;

        identityHashMap.put(key1, 1);
        identityHashMap.put(key2, 2);

        System.out.println(identityHashMap.get(key1)); // 1
        System.out.println(identityHashMap.get(key2)); // 2
        /**
         * 在示例代码中，我们创建了一个 IdentityHashMap 对象 identityHashMap，并向其中添加了两个键值对，
         * 这两个键的引用指向了不同的 String 对象，但是它们的内容是相同的。当使用这两个键获取对应的值时，可以看到 IdentityHashMap 正确地返回了对应的值。
         *
         * 需要注意的是，虽然这两个键的内容相同，但 IdentityHashMap 认为它们是不同的键，因为它依赖于引用相等性而不是对象内容相等性
         */
    }
}
