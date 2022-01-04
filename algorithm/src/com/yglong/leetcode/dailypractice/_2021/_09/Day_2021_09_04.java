package com.yglong.leetcode.dailypractice._2021._09;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class Day_2021_09_04 {
    public static void main(String[] args) {
        System.out.println(fib5(36));
    }

    private static final int MOD = 1000000007;

    /**
     * 递归
     * 会超时
     */
    public static int fib1(int n) {
        if (n <= 1) {
            return n;
        }
        return (fib1(n - 1) + fib1(n - 2)) % MOD;
    }

    /**
     * 备忘录 + 递归
     */
    public static int fib2(int n) {
        Map<Integer, Integer> memo = new HashMap();
        memo.put(0, 0);
        memo.put(1, 1);
        if (memo.containsKey(n)) {
            return memo.get(n);
        } else {
            int ret = (fib2(n - 1) + fib2(n - 2)) % MOD;
            memo.put(n, ret);
            return ret;
        }
    }

    /**
     * 备忘录 + 循环
     */
    public static int fib3(int n) {
        Map<Integer, Integer> memo = new HashMap();
        memo.put(0, 0);
        memo.put(1, 1);
        for (int i = 2; i <= n; i++) {
            memo.put(i, (memo.get(i - 1) + memo.get(i - 2)) % MOD);
        }
        return memo.get(n);
    }

    /**
     * 动态规划
     */
    public static int fib4(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        return dp[n];
    }

    /**
     * 优化动态规划, 减少内存使用
     */
    public static int fib5(int n) {
        if (n <= 1) {
            return n;
        }
        int n2 = 0; // 保存fib(n-2)
        int n1 = 1; // 保存fib(n-1)
        int ret = 0;
        for (int i = 2; i <= n; i++) {
            ret = (n1 + n2) % MOD;
            n2 = n1;
            n1 = ret;
        }
        return ret;
    }
}
