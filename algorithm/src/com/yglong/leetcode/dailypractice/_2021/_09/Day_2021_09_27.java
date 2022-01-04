package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 639. 解码方法 II
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/decode-ways-ii/
 */
public class Day_2021_09_27 {
    public static void main(String[] args) {
        System.out.println(numDecodings("2*"));
        System.out.println(numDecodings("2839"));
    }

    private static final int MOD = 1000000007;

    public static int numDecodings(String s) {
        int n = s.length();
        // a为f(i-2)， b为f(i-1)，c为f(i)
        long a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; i++) {
            // 当前字符
            char ch1 = s.charAt(i - 1);
            c = (b * oneDigit(ch1)) % MOD;
            if (i > 1) {
                // 前一个字符
                char ch2 = s.charAt(i - 2);
                c = (c + a * twoDigit(ch1, ch2)) % MOD;
            }
            a = b;
            b = c;
        }
        return (int) c;
    }

    private static int oneDigit(char ch) {
        if (ch == '0') {
            return 0;
        }
        return ch == '*' ? 9 : 1;
    }

    private static int twoDigit(char ch1, char ch2) {
        if (ch2 == '*' && ch1 == '*') {
            return 15;
        }
        if (ch2 == '*') {
            return ch1 <= '6' ? 2 : 1;
        }
        if (ch1 == '*') {
            if (ch2 == '1') {
                return 9;
            }
            if (ch2 == '2') {
                return 6;
            }
            return 0;
        }
        return (ch2 != '0' && (ch2 - '0') * 10 + (ch1 - '0') <= 26) ? 1 : 0;
    }
}
