package com.example.practicejavase.collection.list;

import java.util.ArrayList;

/**
 * ArrayList是Java集合库中的一种动态数组，它提供了可变长度的数组实现，支持高效地随机访问元素和插入、删除操作。
 *
 * ArrayList的使用场景：
 *
 * 需要频繁随机访问元素的场景。由于ArrayList底层使用数组实现，可以通过下标直接访问元素，因此对于需要经常随机访问元素的场景，ArrayList是一个很好的选择。
 * 需要高效的插入和删除操作的场景。相比于LinkedList，ArrayList在中间位置插入和删除元素的性能更高，因为ArrayList不需要像链表那样重新连接节点，只需要移动后续元素的位置。
 * 需要支持动态调整长度的场景。ArrayList的长度是可调整的，可以根据需求动态地增加或减少。这在需要根据实际情况动态调整数组长度的应用中非常有用。
 */
public class ArrayListExample {

    public static void main(String[] args) {
        // 创建一个ArrayList对象
        ArrayList<String> arrayList = new ArrayList<>();

        // 添加元素
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Orange");

        // 获取大小
        System.out.println("Size: " + arrayList.size());

        // 获取指定位置的元素
        System.out.println("Element at index 1: " + arrayList.get(1));

        // 删除指定元素
        arrayList.remove("Banana");

        // 遍历并打印所有元素
        for (String element : arrayList) {
            System.out.println("Element: " + element);
        }
    }
}
