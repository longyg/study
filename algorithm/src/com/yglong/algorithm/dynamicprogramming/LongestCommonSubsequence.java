package com.yglong.algorithm.dynamicprogramming;

/**
 * 1143. 最长公共子序列
 * <p>
 * <p>
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * <p>
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * <p>
 * 输出：3
 * <p>
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * <p>
 * 输出：3
 * <p>
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * <p>
 * 输出：0
 * <p>
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * <p>
 * text1 和text2 仅由小写英文字符组成。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 */
public class LongestCommonSubsequence {

    // 1143. 最长公共子序列
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
