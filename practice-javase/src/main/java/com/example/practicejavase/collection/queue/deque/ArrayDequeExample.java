package com.example.practicejavase.collection.queue.deque;

import java.util.ArrayDeque;

/**
 * ArrayDeque是Java中的一个双端队列（Deque）实现类，它可以在队列的两端进行插入和删除操作。ArrayDeque是一个非线程安全的类，它不支持同步操作。
 *
 * ArrayDeque适用于以下场景：
 *
 * 需要在队列的两端快速插入和删除元素的场景，因为ArrayDeque的插入和删除操作都是在常数时间内完成，即O(1)时间复杂度；
 * 需要使用栈（先进后出）或队列（先进先出）的场景
 */
public class ArrayDequeExample {
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();

        // 在队列尾部入队
        deque.offer("Element 1");
        deque.offer("Element 2");
        deque.offer("Element 3");

        // 在队列头部出队
        String element = deque.poll();
        System.out.println("Polled element: " + element);

        // 查看队列头部的元素，但不出队
        String peekedElement = deque.peek();
        System.out.println("Peeked element: " + peekedElement);

        // 遍历队列
        System.out.println("Elements in the deque:");
        for (String e : deque) {
            System.out.println(e);
        }
        /**
         * ArrayDeque既可以作为队列使用（先进先出），也可以作为栈使用（先进后出）。当我们使用offer和poll方法时，ArrayDeque就变成了队列。
         * 如果我们使用push和pop方法，ArrayDeque就变成了栈。你可以根据具体的使用需求选择操作方法来实现队列或栈的功能。
         */
    }
}
