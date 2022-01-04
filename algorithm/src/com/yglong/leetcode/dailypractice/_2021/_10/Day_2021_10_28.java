package com.yglong.leetcode.dailypractice._2021._10;

import java.util.HashMap;
import java.util.Map;

/**
 * 869. 重新排序得到 2 的幂
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/reordered-power-of-2/
 */
public class Day_2021_10_28 {
    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(16));
        System.out.println(reorderedPowerOf2(24));
    }

    static Map<String, Map<Character, Integer>> all = new HashMap<>();

    static {
        int power = 0;
        int num = 1;
        while (num <= (int) 1e9 + 10) {
            String s = String.valueOf(num);
            Map<Character, Integer> m = new HashMap<>();
            for (char c : s.toCharArray()) {
                m.put(c, m.getOrDefault(c, 0) + 1);
            }
            all.put(s, m);
            num = (int) Math.pow(2, power++);
        }
    }

    public static boolean reorderedPowerOf2(int n) {
        String ns = String.valueOf(n);
        // 保存输入整数的单个数字出现的次数
        Map<Character, Integer> sMap = new HashMap<>();
        for (char c : ns.toCharArray()) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }

        for (String s : all.keySet()) {
            if (ns.length() != s.length()) continue;
            if (isMatch(sMap, all.get(s))) return true;
        }
        return false;
    }

    private static boolean isMatch(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        for (Map.Entry<Character, Integer> entry : sMap.entrySet()) {
            if (!tMap.containsKey(entry.getKey())) return false;
            if (!tMap.get(entry.getKey()).equals(entry.getValue())) return false;
        }
        return true;
    }
}
