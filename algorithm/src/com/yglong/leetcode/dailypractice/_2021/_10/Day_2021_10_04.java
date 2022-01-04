package com.yglong.leetcode.dailypractice._2021._10;

/**
 * 482. 密钥格式化
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/license-key-formatting/
 */
public class Day_2021_10_04 {
    public static void main(String[] args) {
        System.out.println(licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(licenseKeyFormatting("2-5g-3-J", 2));
    }

    public static String licenseKeyFormatting(String s, int k) {
        int n = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') continue;
            if (n == k) {
                sb.append('-').append(c);
                n = 1;
                continue;
            }
            sb.append(c);
            n++;
        }
        return sb.reverse().toString().toUpperCase();
    }
}
