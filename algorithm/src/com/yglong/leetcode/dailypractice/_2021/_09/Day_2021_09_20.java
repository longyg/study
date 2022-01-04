package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 673. 最长递增子序列的个数
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
 */
public class Day_2021_09_20 {
    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
    }

    /**
     * 动态规划
     */
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        int maxLen = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i];
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }
}
