package com.yglong.leetcode.dailypractice._2021_08;

import java.util.*;

/**
 * 516. 最长回文子序列
 * <p>
 * <p>
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * <p>
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：s = "bbbab"
 * <p>
 * 输出：4
 * <p>
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：s = "cbbd"
 * <p>
 * 输出：2
 * <p>
 * 解释：一个可能的最长回文子序列为 "bb" 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= s.length <= 1000
 * <p>
 * s 仅由小写英文字母组成
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 */
public class Day_2021_08_12 {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
        System.out.println(longestPalindromeSubseq("cbbd"));
        System.out.println(longestPalindromeSubseq("aabaa"));
    }

    /**
     * 首先搞清楚什么是回文序列：
     * <p>
     * 如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列
     * <p>
     * 动态规划
     * <p>
     * 状态转移方程：
     * - 如果s[i] == s[j]: dp[i][j] = dp[i+1][j-1] + 2
     * - 如果s[i] != s[j]: dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
