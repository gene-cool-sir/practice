package com.example.practicejavase.collection;

import com.example.practicejavase.collection.uri.JarUtils;

import java.util.Map;
import java.util.NavigableMap;

public class Demo01 {

    public static void main(String[] args) {
       // NavigableMap
        String classPath = JarUtils.getClassPath(Demo01.class);
        System.out.println(classPath);
    }
}
