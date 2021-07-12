package com.yglong.leetcode.dailypractice._2021_06;

/**
 * 有一堆石头，用整数数组stones 表示。其中stones[i] 表示第 i 块石头的重量。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为x 和y，且x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果x == y，那么两块石头都会被完全粉碎；
 * <p>
 * 如果x != y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
 * <p>
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 */
public class Day_2021_06_08 {
    public static void main(String[] args) {
        int[] stones = new int[]{2, 7, 4, 1, 8, 1};
        System.out.println(findMin(stones));
    }

    /**
     * 动态规划
     */
    public static int findMin(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int half = sum / 2;
        boolean[][] dp = new boolean[stones.length + 1][half + 1];
        dp[0][0] = true;
        for (int i = 0; i < stones.length; ++i) {
            for (int j = 0; j <= half; ++j) {
                if (j < stones[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }
        for (int j = half; ; --j) {
            if (dp[stones.length][j]) {
                return sum - 2 * j;
            }
        }
    }
}
