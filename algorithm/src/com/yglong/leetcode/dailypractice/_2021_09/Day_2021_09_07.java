package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 1221. 分割平衡字符串
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/
 */
public class Day_2021_09_07 {
    public static void main(String[] args) {
        System.out.println(balancedStringSplit("RLRRLLRLRL")); // 4
        System.out.println(balancedStringSplit("RLLLLRRRLR")); // 3
        System.out.println(balancedStringSplit("LLLLRRRR")); // 1
        System.out.println(balancedStringSplit("RLRRRLLRLL")); // 2
    }

    public static int balancedStringSplit(String s) {
        int n = s.length();
        if (n < 2) {
            return 0;
        }

        int d = 0; // 记录L和R的个数之差
        int cnt = 0; // 记录平衡字符串的个数
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') {
                d++;
            } else {
                d--;
            }
            // 当前L和R的个数之差为0时，则找到一个平衡字符串
            if (d == 0) {
                cnt++;
            }
        }
        return cnt;
    }
}
