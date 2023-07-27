package com.example.practicejavase.collection.set;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * LinkedHashSet是Java中的集合类，它是HashSet的一个子类，具有HashSet的无重复元素特性，并且保留了元素的插入顺序。它内部使用链表维护元素的顺序。
 *
 * LinkedHashSet适用于以下场景：
 *
 * 需要存储一组元素，且不允许重复的场景；
 * 需要保留元素的插入顺序的场景；
 * 不需要频繁进行大量的插入和删除操作，因为LinkedHashSet的插入和删除操作比HashSet稍慢。
 */
public class LinkedHashSetExample {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>();

        // 添加元素
        set.add("Element 1");
        set.add("Element 2");
        set.add("Element 3");

        // 判断是否包含元素
        boolean containsElement = set.contains("Element 2");
        System.out.println("Contains element \"Element 2\": " + containsElement);

        // 删除元素
        set.remove("Element 1");

        // 遍历集合
        System.out.println("Elements in the set:");
        for (String element : set) {
            System.out.println(element);
        }

        /**
         * LinkedHashSet相对于HashSet来说，由于维护了元素的插入顺序，所以插入和删除操作的性能可能稍慢一些。
         * 如果不需要保持元素的插入顺序，可以考虑使用HashSet，它的性能更高。
         * 另外，LinkedHashSet的元素同样需要实现hashCode()和equals()方法。
         */
    }
}
