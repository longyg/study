package com.yglong.leetcode.dailypractice._2021_10;

/**
 * 405. 数字转换为十六进制数
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/
 */
public class Day_2021_10_02 {
    public static void main(String[] args) {
        System.out.println(toHex(26));
        System.out.println(toHex(31));
    }

    public static String toHex(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            int val = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + (val - 10));
                sb.append(digit);
            }
        }
        return sb.toString();
    }
}

