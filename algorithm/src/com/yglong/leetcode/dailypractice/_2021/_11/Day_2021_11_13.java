package com.yglong.leetcode.dailypractice._2021._11;

/**
 * 520. 检测大写字母
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/detect-capital/
 */
public class Day_2021_11_13 {
    public static void main(String[] args) {
        System.out.println(detectCapitalUse("USA"));
        System.out.println(detectCapitalUse("Google"));
        System.out.println(detectCapitalUse("hello"));
        System.out.println(detectCapitalUse("wOrld"));
        System.out.println(detectCapitalUse("FlaG"));
    }

    public static boolean detectCapitalUse(String word) {
        int n = word.length();
        int c = -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                c = i;
                cnt++;
            }
        }
        return c == -1 || c == 0 || cnt == n;
    }
}
