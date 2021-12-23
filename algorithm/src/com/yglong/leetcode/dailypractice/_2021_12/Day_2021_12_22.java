package com.yglong.leetcode.dailypractice._2021_12;

/**
 * 686. 重复叠加字符串匹配
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * <p>
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/repeated-string-match
 */
public class Day_2021_12_22 {

    public static void main(String[] args) {
        System.out.println(repeatedStringMatch("abc", "cabcabca"));
        System.out.println(repeatedStringMatch("abcd", "cdabcdab"));
        System.out.println(repeatedStringMatch("a", "aa"));
        System.out.println(repeatedStringMatch("a", "a"));
        System.out.println(repeatedStringMatch("abc", "xyz"));
    }

    public static int repeatedStringMatch(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        int cnt = lenB / lenA; // 最少重复叠加次数
        StringBuilder sb = new StringBuilder();
        sb.append(a.repeat(cnt));
        if (sb.toString().contains(b)) {
            return cnt;
        }

        // 最多重复叠加次数为最小叠加次数 + 2
        for (int i = 1; i <= 2; i++) {
            sb.append(a);
            if (sb.toString().contains(b)) {
                return cnt + i;
            }
        }
        return -1;
    }
}
