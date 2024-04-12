package com.example.practicejavase.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

public class DemoCaffeine {

    public static LoadingCache<String, String> buildCaffeine() {
        return Caffeine.newBuilder()
                .maximumSize(2)
                .build( (t) -> {
                    return "aaa";
                });
    }

    public static void main(String[] args) {
        LoadingCache<String, String> cache = buildCaffeine();
        cache.put("bb","123");
        String a = cache.get("token");
        String a1 = cache.get("token");
        String b = cache.get("token1");
        String b1 = cache.get("bb");
        System.out.println(a);
        System.out.println(a1);
        System.out.println(b);
        System.out.println(b1);

    }
}
