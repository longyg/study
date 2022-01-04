package com.yglong.leetcode.dailypractice._2021._11;

import java.util.HashMap;
import java.util.Map;

/**
 * 1218. 最长定差子序列
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/
 */
public class Day_2021_11_05 {

    public static void main(String[] args) {
        System.out.println(longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2));
    }

    /**
     * 暴力
     */
    public static int longestSubsequence1(int[] arr, int difference) {
        int n = arr.length;
        int max = 1;
        for (int i = n - 1; i >= 1; i--) {
            int curMax = 1;
            int cur = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (cur - arr[j] == difference) {
                    curMax++;
                    cur = arr[j];
                }
            }
            if (curMax > max) {
                max = curMax;
            }
        }
        return max;
    }

    /**
     * 动态规划
     */
    public static int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        Map<Integer, Integer> dp = new HashMap<>();
        for (int v : arr) {
            dp.put(v, dp.getOrDefault(v - difference, 0) + 1);
            ans = Math.max(ans, dp.get(v));
        }
        return ans;
    }

}
