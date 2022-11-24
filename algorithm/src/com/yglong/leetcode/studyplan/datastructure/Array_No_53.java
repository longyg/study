package com.yglong.leetcode.studyplan.datastructure;

/**
 * 53. 最大子数组和
 * <p>
 * <p>
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组 是数组中的一个连续部分。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-subarray/
 */
public class Array_No_53 {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArray(new int[]{1}));
        System.out.println(maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }

    /**
     * 前缀和
     * <p>
     * 超时
     */
    public static int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n];
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int max = sum[0];
        for (int i = 1; i < n; i++) {
            if (sum[i] > max) {
                max = sum[i];
            }
            for (int j = 0; j < i; j++) {
                int gap = sum[i] - sum[j];
                if (gap > max) {
                    max = gap;
                }
            }
        }
        return max;
    }

    /**
     * 动态规划
     */
    public static int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

}
