package com.yglong.leetcode.dailypractice._2021_07;

import java.util.*;

/**
 * 451. 根据字符出现频率排序
 * <p>
 * <p>
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 */
public class Day_2021_07_03 {

    public static void main(String[] args) {
        System.out.println(frequencySort("Aabb"));
    }

    /**
     * 利用map保存每个字符的数量，再排序输出
     */
    public static String frequencySort(String s) {
        if (s.equals("")) {
            return s;
        }
        // 统计词频
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int freq = map.getOrDefault(c, 0) + 1;
            map.put(c, freq);
        }

        // 按词频排序
        List<Character> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));

        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            int freq = map.get(c);
            // 如果某个字符出现频率大于1，则连续输出相同字符freq次
            for (int i = 0; i < freq; i++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }


}
