package com.example.practicejavase.collection.set;

import java.util.TreeSet;

/**
 * TreeSet是Java中的集合类，它是基于红黑树（一种自平衡二叉搜索树）实现的，可以存储不重复的元素，
 * 并且以有序的方式保存元素（默认是自然排序，也可以使用Comparator自定义排序规则）。
 *
 * TreeSet适用于以下场景：
 *
 * 需要存储一组元素，并且希望以有序的方式进行遍历和查找操作的场景；
 * 需要快速查找最小值或最大值的场景；
 * 需要按照自定义排序规则对元素进行排序的场景。
 */
public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();

        // 添加元素
        set.add(3);
        set.add(1);
        set.add(2);

        // 判断是否包含元素
        boolean containsElement = set.contains(2);
        System.out.println("Contains element 2: " + containsElement);

        // 删除元素
        set.remove(1);

        // 遍历集合
        System.out.println("Elements in the set:");
        for (int element : set) {
            System.out.println(element);
        }

        // 获取最小值
        int minElement = set.first();
        System.out.println("Minimum element: " + minElement);

        // 获取最大值
        int maxElement = set.last();
        System.out.println("Maximum element: " + maxElement);

        /**
         * ，TreeSet的元素必须是可比较的，因此元素类必须实现Comparable接口，或者在创建TreeSet时指定自定义的Comparator。这样才能保证元素在树中的有序性。
         * 另外，由于TreeSet使用红黑树实现，插入、删除和查找操作的时间复杂度为O(logN)，比HashSet和LinkedHashSet略慢。
         * 但是，TreeSet具有有序性和快速获取最小值和最大值的优势，因此在需要有序集合的场景下是一个很好的选择。
         */
    }
}
