package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 583. 两个字符串的删除操作
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings/
 */
public class Day_2021_09_25 {
    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
    }

    /**
     * 动态规划
     * <p>
     * 根据题意，可以转为求两个字符串的最长公共子序列的长度
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return m + n - 2 * dp[m][n];
    }
}
