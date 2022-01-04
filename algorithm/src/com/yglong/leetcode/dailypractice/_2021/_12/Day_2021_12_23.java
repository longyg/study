package com.yglong.leetcode.dailypractice._2021._12;

/**
 * 1044. 最长重复子串
 * <p>
 * <p>
 * 难度：困难
 * <p>
 * <p>
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 * <p>
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 * <p>
 *  
 * 链接：https://leetcode-cn.com/problems/longest-duplicate-substring
 */
public class Day_2021_12_23 {
    public static void main(String[] args) {
        System.out.println(longestDupSubstring("banana"));
    }

    /**
     * 暴力法
     */
    public static String longestDupSubstring(String s) {
        int n = s.length();
        String ans = "";
        int max = 0;
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            int index = s.indexOf(c);
            while (index > -1 && index < i) {
                StringBuilder sb = new StringBuilder();
                int j = i, k = index, len = 0;
                while (k < n && j < n && s.charAt(j) == s.charAt(k)) {
                    sb.append(s.charAt(j));
                    len++;
                    j++;
                    k++;
                }
                if (max < len) {
                    max = len;
                    ans = sb.toString();
                    i = Math.max(k, i);
                }
                index = s.indexOf(c, index + 1);
            }
        }
        return ans;
    }
}
