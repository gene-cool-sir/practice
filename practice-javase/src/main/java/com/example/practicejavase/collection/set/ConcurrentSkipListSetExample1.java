package com.example.practicejavase.collection.set;

import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkipListSetExample1 {
        public static void main(String[] args) {
            ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<>();

            // 按照字母顺序插入数据
            set.add("b");
            set.add("a");
            set.add("d");
            set.add("c");

            // 按照字母顺序遍历数据
            for (String element : set) {
                System.out.println(element);
            }
        }
    }