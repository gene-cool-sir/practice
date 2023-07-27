package com.example.practicejavase.collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 * HashSet是Java中的集合类，它是基于哈希表实现的，可以存储不重复的元素，并不保证元素的顺序。
 * HashSet的添加、删除和查找操作的时间复杂度都是近似常量级的，即O(1)。
 *
 * HashSet适用于以下场景：
 *
 * 需要存储一组元素，且不允许重复的场景；
 * 需要快速进行元素的添加、删除和查找操作的场景；
 * 不需要对元素的顺序有要求的场景。
 */
public class HashSetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

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
    }
}
