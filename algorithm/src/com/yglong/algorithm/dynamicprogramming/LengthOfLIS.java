package com.yglong.algorithm.dynamicprogramming;

/**
 * 300. 最长递增子序列
 * <p>
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * <p>
 * 输出：4
 * <p>
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * <p>
 * 输出：4
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * <p>
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 2500
 * <p>
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * 进阶：
 * <p>
 * <p>
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * <p>
 * 你能将算法的时间复杂度降低到O(n log(n)) 吗?
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class LengthOfLIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS2(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLIS2(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lengthOfLIS2(new int[]{7, 7, 7, 7, 7, 7, 7}));
        System.out.println(lengthOfLIS2(new int[]{4, 10, 4, 3, 8, 9}));
    }

    /**
     * 动态规划
     */
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 贪心 + 二分查找
     */
    public static int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int len = 1;
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int k = binarySearch(d, nums[i], len);
                d[k + 1] = nums[i];
            }
        }
        return len;
    }

    private static int binarySearch(int[] d, int num, int high) {
        int low = 1;
        int pos = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (d[mid] < num) {
                pos = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return pos;
    }
}
