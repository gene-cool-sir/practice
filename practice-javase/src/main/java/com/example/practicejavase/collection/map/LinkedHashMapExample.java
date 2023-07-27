package com.example.practicejavase.collection.map;

import java.util.*;

/**
 * LinkedHashMap 的主要特点如下：
 *
 * 有序性：LinkedHashMap 维护了一个插入顺序的双向链表，这意味着它会按照元素的插入顺序进行迭代和获取。
 * 由于有序性，LinkedHashMap 在某些场景下比 HashMap 更适用，尤其是需要保持元素的顺序或按照元素插入顺序遍历的场景。
 *
 * 性能：LinkedHashMap 的性能与 HashMap 相似，查询和插入的时间复杂度为 O(1)，但在迭代和维护有序性方面稍有影响。
 * 由于需要维护链表结构，LinkedHashMap 在插入和删除元素时比 HashMap 略慢。
 *
 * 访问顺序：除了插入顺序，LinkedHashMap 还提供了基于访问顺序的迭代方式。通过在构造 LinkedHashMap 时设置 accessOrder 参数为 true，
 * 可以启用基于访问顺序的迭代方式，即最近访问的元素将排在最后，通过迭代或获取元素时，将会将最近访问的元素移到链表的末尾。这在实现缓存和 LRU Cache 等场景中非常有用。
 */
public class LinkedHashMapExample {
    public static void main(String[] args) {
        // 以插入顺序构造 LinkedHashMap
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put("one", 1);
        linkedHashMap.put("two", 2);
        linkedHashMap.put("three", 3);

        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
