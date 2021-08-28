package com.yglong.javabasic.collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        // 按插入顺序对key排序
        LinkedHashMap<String, Integer> m = new LinkedHashMap<>();
        m.put("c", 3);
        m.put("a", 1);
        m.put("b", 2);
        m.put("e", 2);
        m.get("b");
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        System.out.println("===============");
        // 按访问顺序对key排序
        LinkedHashMap<String, Integer> m2 = new LinkedHashMap<>(16, 0.75f, true);
        m2.put("c", 1);
        m2.put("b", 2);
        m2.put("d", 3);
        m2.put("e", 1);
        m2.get("c");
        m2.get("e");
        m2.put("f", 5);
        for (Map.Entry<String, Integer> e : m2.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}
