package com.yglong.leetcode.dailypractice._2021._11;

import java.util.HashMap;
import java.util.Map;

/**
 * 397. 整数替换
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/integer-replacement/
 * <p>
 * 输入：n = 8
 * 输出：3
 * 解释：8 -> 4 -> 2 -> 1
 */
public class Day_2021_11_19 {

    public static void main(String[] args) {
        System.out.println(new Day_2021_11_19().integerReplacement(8)); // 3
        System.out.println(new Day_2021_11_19().integerReplacement(7)); // 4
        System.out.println(new Day_2021_11_19().integerReplacement(4)); // 2
    }

    /**
     * 动态规划
     */
    public static int integerReplacement1(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;  // n = 1
        // n = 2 开始
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2] + 1;
            } else {
                // 比较n-1与n+1后的谁的步骤少
                dp[i] = Math.min(dp[i - 1], dp[(i + 1) / 2] + 1) + 1;
            }
        }
        return dp[n];
    }

    Map<Integer, Integer> memo = new HashMap<>();

    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (!memo.containsKey(n)) {
            if (n % 2 == 0) {
                memo.put(n, 1 + integerReplacement(n / 2));
            } else {
                memo.put(n, 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)));
            }
        }
        return memo.get(n);
    }
}
