package com.yglong.leetcode.dailypractice._2021._11;

import java.util.HashMap;
import java.util.Map;

/**
 * 299. 猜数字游戏
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/bulls-and-cows/
 */
public class Day_2021_11_08 {
    public static void main(String[] args) {
        System.out.println(getHint("1807", "7810"));
        System.out.println(getHint("1123", "0111"));
        System.out.println(getHint("1", "0"));
        System.out.println(getHint("1", "1"));
    }

    public static String getHint(String secret, String guess) {
        int cntA = 0, cntB = 0;
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        int i = 0;
        while (i < guess.length()) {
            if (secret.charAt(i) == guess.charAt(i)) {
                cntA++;
            } else {
                m1.put(secret.charAt(i), m1.getOrDefault(secret.charAt(i), 0) + 1);
                m2.put(guess.charAt(i), m2.getOrDefault(guess.charAt(i), 0) + 1);
            }
            i++;
        }
        for (Map.Entry<Character, Integer> entry : m1.entrySet()) {
            if (m2.get(entry.getKey()) != null) {
                cntB += Math.min(entry.getValue(), m2.get(entry.getKey()));
            }
        }
        return cntA + "A" + cntB + "B";
    }
}
