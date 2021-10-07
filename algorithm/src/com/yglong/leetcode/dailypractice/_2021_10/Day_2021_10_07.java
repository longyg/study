package com.yglong.leetcode.dailypractice._2021_10;

/**
 * 434. 字符串中的单词数
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string/
 */
public class Day_2021_10_07 {
    public static void main(String[] args) {
        System.out.println(countSegments(""));
        System.out.println(countSegments(" "));
        System.out.println(countSegments(" af "));
        System.out.println(countSegments(" a b  c  "));
    }

    public static int countSegments(String s) {
        int cnt = 0;
        char last = ' ';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ' && last == ' ') {
                cnt++;
            }
            last = c;
        }
        return cnt;
    }

}
