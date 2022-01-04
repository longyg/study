package com.yglong.leetcode.dailypractice._2021._07;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * <p>
 * <p>
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 * <p>
 * 示例1:
 * <p>
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * <p>
 * 输出: 6
 * <p>
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 */
public class Day_2021_07_17 {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    /**
     * 前缀和方法
     */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int minPrefix = nums[0]; // 前i个元素中最小的前缀和
        int max = nums[0]; // 前i个元素中连续子数组最大和
        int sum = nums[0]; // sum保存数组前i个元素的和
        for (int num : nums) {
            sum += num;
            int curMax = sum - minPrefix;
            if (curMax > max) { // 判断当前连续子数组和是否为最大
                max = curMax; // 如果是，则更新连续子数组最大和
            }
            if (sum < minPrefix) { // 判断当前数组总和是否小于当前最小前缀和
                minPrefix = sum; // 如果是，则更新最小前缀和
            }
        }
        return max;
    }
}
