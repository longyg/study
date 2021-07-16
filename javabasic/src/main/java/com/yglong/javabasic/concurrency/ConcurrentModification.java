package com.yglong.javabasic.concurrency;

import java.util.HashMap;
import java.util.Map;

/**
 * 并发修改异常
 */
public class ConcurrentModification {
    private static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put("" + i, i);
            }
        }).start();

        new Thread(() -> {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }).start();
    }

}
