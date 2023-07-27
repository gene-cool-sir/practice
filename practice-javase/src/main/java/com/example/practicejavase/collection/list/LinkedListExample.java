package com.example.practicejavase.collection.list;

import java.util.LinkedList;

/**
 * LinkedList是Java集合库中的双向链表实现，它提供了可变长度的链表结构，支持高效地插入、删除和查找操作。
 *
 * LinkedList的使用场景：
 *
 * 需要频繁在链表的开头或结尾进行插入和删除操作的场景。LinkedList在插入和删除元素时，只需要修改相邻节点的引用，因此对于链表的开头或结尾的操作，LinkedList具有更高的效率。
 * 需要频繁进行元素的添加和删除操作的场景。相比于ArrayList，LinkedList在中间位置插入和删除元素的性能更高，因为LinkedList不需要像数组那样移动元素的位置。
 * 需要支持双向遍历操作的场景。LinkedList的每个节点都包含前后两个引用，因此可以在双向上进行遍历。这在需要根据上一个节点或下一个节点进行操作的应用中非常有用。
 */
public class LinkedListExample {

    public static void main(String[] args) {
        // 创建一个LinkedList对象
        LinkedList<String> linkedList = new LinkedList<>();

        // 添加元素
        linkedList.add("Apple");
        linkedList.add("Banana");
        linkedList.add("Orange");

        // 获取大小
        System.out.println("Size: " + linkedList.size());

        // 获取第一个元素和最后一个元素
        System.out.println("First element: " + linkedList.getFirst());
        System.out.println("Last element: " + linkedList.getLast());

        // 删除指定元素
        linkedList.remove("Banana");

        // 遍历并打印所有元素
        for (String element : linkedList) {
            System.out.println("Element: " + element);
        }
    }
}
