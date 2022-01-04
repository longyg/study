package com.yglong.leetcode.dailypractice._2021._06;

/**
 * 518. 零钱兑换 II
 * <p>
 * <p>[i
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * <p>
 * <p>
 * 输入: amount = 5, coins = [1, 2, 5]
 * <p>
 * 输出: 4
 * <p>
 * 解释: 有四种方式可以凑成总金额:
 * <p>
 * 5=5
 * <p>
 * 5=2+2+1
 * <p>
 * 5=2+1+1+1
 * <p>
 * 5=1+1+1+1+1
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/coin-change-2
 */
public class Day_2021_06_10 {
    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println(change1(5, coins));
    }

    /**
     * 二维数组动态规划
     */
    public static int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                int coin = coins[i - 1];
                if (j < coin) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                }
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * 优化版：一维数组动态规划
     */
    public static int change1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
}
