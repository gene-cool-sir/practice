package com.example.practicejavase.collection.map;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * TreeMap是Java集合库中的一种有序映射表，它基于红黑树（Red-Black Tree）数据结构实现，保证了键的有序性。
 * TreeMap提供了一系列的方法用于操作键值对，支持高效的插入、删除和查找操作。
 *
 * TreeMap的使用场景：
 *
 * 需要按照键的顺序进行遍历或查找的场景。TreeMap内部使用红黑树作为底层实现，它会根据键的自然顺序或通过自定义的Comparator进行排序，因此遍历TreeMap时可以保证键的有序性。
 * 需要快速插入和删除并能够保持键的有序性的场景。红黑树数据结构提供了高效的插入和删除操作，同时TreeMap在插入和删除后会自动进行平衡操作，以保持红黑树的平衡性。
 * 需要进行范围查找操作的场景。TreeMap提供了一系列的方法用于范围查找，如subMap、headMap和tailMap等，可以根据指定的键范围来获取子映射。
 * 下面是一个TreeMa
 */
public class TreeMapExample {

    public static void main(String[] args) {
        // 创建一个TreeMap对象
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // 添加键值对
        treeMap.put(3, "Apple");
        treeMap.put(1, "Banana");
        treeMap.put(2, "Orange2");
        treeMap.put(4, "Orange4");
        treeMap.put(5, "Orange5");

        // 获取大小
        System.out.println("Size: " + treeMap.size());

        // 获取指定键对应的值
        System.out.println("Value for key 2: " + treeMap.get(2)); // Orange2 保证了有序性

        // 删除键值对
        treeMap.remove(1);

        // 遍历并打印所有键值对
        for (Integer key : treeMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + treeMap.get(key));
        }

        //treeMap.sub,降序
        NavigableMap<Integer, String> integerStringNavigableMap = treeMap.descendingMap();
        // 截取
        NavigableMap<Integer, String> integerStringNavigableMap1 = integerStringNavigableMap.headMap(6, true);
        System.out.println(integerStringNavigableMap1.get(5));

    }
}
