package com.example.practicejavase.collection.set;

import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * NavigableSet是Java中的接口，它是SortedSet的子接口，扩展了一些导航方法（如获取指定范围的子集合、查找最接近给定元素的元素等），可以实现对有序集合的更加灵活的操作。
 *
 * NavigableSet适用于以下场景：
 *
 * 需要对有序集合进行更复杂的导航和操作的场景；
 * 需要获取某个范围内的子集合的场景；
 * 需要查找最接近给定元素的元素的场景。
 */
public class NavigableSetExample {
    public static void main(String[] args) {
        NavigableSet<Integer> set = new TreeSet<>();

        // 添加元素
        set.add(3);
        set.add(1);
        set.add(5);
        set.add(2);
        set.add(4);

        // 获取小于等于给定元素的最大元素
        int floorElement = set.floor(3);
        System.out.println("Floor element of 3: " + floorElement);

        // 获取小于给定元素的最大元素
        int lowerElement = set.lower(3);
        System.out.println("Lower element of 3: " + lowerElement);

        // 获取大于等于给定元素的最小元素
        int ceilingElement = set.ceiling(3);
        System.out.println("Ceiling element of 3: " + ceilingElement);

        // 获取大于给定元素的最小元素
        int higherElement = set.higher(3);
        System.out.println("Higher element of 3: " + higherElement);

        // 获取子集合
        NavigableSet<Integer> subSet = set.subSet(2, true, 4, true);
        System.out.println("SubSet: " + subSet);

        // 获取倒序遍历的迭代器
        System.out.println("Descending order iterator: ");
        for (int element : set.descendingSet()) {
            System.out.println(element);
        }

        /**
         * 在这个示例中，我们首先创建了一个TreeSet，并向集合中添加5个元素。然后使用NavigableSet提供的一些导航方法：
         *
         * floor：获取小于等于给定元素的最大元素；
         * lower：获取小于给定元素的最大元素；
         * ceiling：获取大于等于给定元素的最小元素；
         * higher：获取大于给定元素的最小元素；
         * subSet：获取给定范围内的子集合；
         * descendingSet：获取倒序遍历的迭代器。
         * 通过这些导航方法，我们可以更灵活地操作和遍历有序集合。
         *
         * 需要注意的是，NavigableSet是一个接口，常用的实现类有TreeSet。
         * 另外，NavigableSet也继承了SortedSet的方法，可以通过SortedSet获取元素的第一个和最后一个元素。它提供了对有序集合的更加灵活的操作和导航能力
         */
    }
}
