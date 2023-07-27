package com.example.practicejavase.collection.map;

import java.util.*;

/**
 * NavigableMap 在生产项目中有多种适用场景，其中包括以下几个常见的场景：
 *
 * 范围查询：NavigableMap 提供了 subMap 方法，可以根据范围检索映射表中的键值对。
 * 这在需要根据一定的范围进行数据查询和处理的情况下非常有用，例如按时间范围检索日志记录、按分数范围查询学生成绩等。
 *
 * 导航和搜索：NavigableMap 提供了多个导航方法，包括 lowerKey、floorKey、ceilingKey 和 higherKey 等。
 * 这些方法使得在有序键值对中执行导航和搜索操作变得非常方便，例如查找前后相邻的键、查找大于或小于给定键的键等。
 *
 * 排序和统计：NavigableMap 本身就是一个有序的映射表，它能够自动根据键进行排序。这使得在需要按照键进行排序的场景下，可以直接使用 NavigableMap，
 * 而无需额外的排序步骤。此外，NavigableMap 还提供了一些统计信息的方法，如 firstKey 和 lastKey，可用于获取第一个和最后一个键。
 *
 * 缓存和过期策略：在需要使用缓存或记录数据的过期时间的场景下，NavigableMap 可以作为一个便利的容器。可以将键设置为数据的过期时间，
 * 在定时任务或其他操作中，通过导航方法获取到过期的键，并进行数据清理和处理。
 *
 * 时间线管理：对于时间线管理和事件调度的场景，NavigableMap 是一个有用的工具。通过使用时间戳或事件时间作为键，
 * 可以将事件按照时间顺序存储在映射表中，然后使用导航方法查找相应时间范围内的事件。
 *
 * 注意，以上仅是一些常见的应用场景示例，实际上 NavigableMap 在更复杂的数据处理和导航操作中也非常有用。
 * 根据具体的需求，可以灵活应用 NavigableMap 来解决相关问题。
 */
public class NavigableMapExample {
    public static void main(String[] args) {
        NavigableMap<String, Integer> navigableMap = new TreeMap<>();

        navigableMap.put("apple", 7);
        navigableMap.put("banana", 2);
        navigableMap.put("cherry", 3);
        navigableMap.put("date", 4);
        navigableMap.put("grape", 5);

        // NavigableMap 本身就是一个有序的映射表
        System.out.println(navigableMap.lowerKey("cherry")); // banana
        System.out.println(navigableMap.floorKey("cherry")); // cherry
        System.out.println(navigableMap.ceilingKey("cherry")); // cherry
        System.out.println(navigableMap.higherKey("cherry")); // date
        System.out.println(navigableMap.descendingMap()); // {grape=5, date=4, cherry=3, banana=2, apple=1}
        System.out.println(navigableMap.navigableKeySet()); // [apple, banana, cherry, date, grape]
        System.out.println(navigableMap.subMap("banana", true, "date", true)); // {banana=2, cherry=3, date=4}

    }
}
