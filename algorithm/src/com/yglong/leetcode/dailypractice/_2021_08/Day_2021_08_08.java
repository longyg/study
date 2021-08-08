package com.yglong.leetcode.dailypractice._2021_08;

import java.util.HashMap;
import java.util.Map;

/**
 * 1137. 第 N 个泰波那契数
 * <p>
 * <p>
 * 泰波那契序列Tn定义如下：
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数n，请返回第 n 个泰波那契数Tn 的值。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 4
 * <p>
 * 输出：4
 * <p>
 * 解释：
 * <p>
 * T_3 = 0 + 1 + 1 = 2
 * <p>
 * T_4 = 1 + 1 + 2 = 4
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 25
 * <p>
 * 输出：1389537
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 0 <= n <= 37
 * <p>
 * 答案保证是一个 32 位整数，即answer <= 2^31 - 1。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
 */
public class Day_2021_08_08 {
    public static void main(String[] args) {
        System.out.println(tribonacci(4));
        System.out.println(tribonacci(25));
    }

    /**
     * 记忆 + 递归法
     */
    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }

        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 1);
        memo.put(2, 1);

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int res = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        memo.put(n, res);
        return res;
    }

    /**
     * 动态规划 （迭代）
     */
    public static int tribonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int ti = 0;
        int ti_3 = 0, ti_2 = 1, ti_1 = 1;
        for (int i = 3; i <= n; i++) {
            ti = ti_1 + ti_2 + ti_3;
            ti_3 = ti_2;
            ti_2 = ti_1;
            ti_1 = ti;
        }
        return ti;
    }
}
