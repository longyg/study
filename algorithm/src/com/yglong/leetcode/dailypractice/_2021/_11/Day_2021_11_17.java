package com.yglong.leetcode.dailypractice._2021._11;

import java.util.*;

/**
 * 318. 最大单词长度乘积
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths/
 */
public class Day_2021_11_17 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"})); // 16
        System.out.println(maxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"})); // 4
        System.out.println(maxProduct(new String[]{"a", "aa", "aaa", "aaaa"})); // 0
    }

    /**
     * 哈希表
     */
    public static int maxProduct(String[] words) {
        Map<Character, Set<String>> m = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                Set<String> set = m.getOrDefault(c, new HashSet<>());
                set.add(word);
                m.put(c, set);
            }
        }
        int max = 0;
        for (String word : words) {
            int len = word.length();
            Set<String> s = new HashSet<>(Arrays.asList(words));
            for (char c : word.toCharArray()) {
                s.removeAll(m.get(c));
            }
            for (String s1 : s) {
                int x = len * s1.length();
                max = Math.max(x, max);
            }
        }
        return max;
    }
}
