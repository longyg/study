package com.yglong.leetcode.dailypractice._2021._07;

import java.util.Arrays;

/**
 * 1877. 数组中最大数对和的最小值
 * <p>
 * <p>
 * 一个数对(a,b)的 数对和等于a + b。最大数对和是一个数对数组中最大的数对和。
 * <p>
 * 比方说，如果我们有数对(1,5)，(2,3)和(4,4)，最大数对和为max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8。
 * <p>
 * <p>
 * 给你一个长度为 偶数n的数组nums，请你将 nums中的元素分成 n / 2个数对，使得：
 * <p>
 * nums中每个元素恰好在 一个数对中，且
 * <p>
 * 最大数对和的值 最小。
 * <p>
 * 请你在最优数对划分的方案下，返回最小的 最大数对和。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [3,5,2,3]
 * <p>
 * 输出：7
 * <p>
 * 解释：数组中的元素可以分为数对 (3,3) 和 (5,2) 。
 * <p>
 * 最大数对和为 max(3+3, 5+2) = max(6, 7) = 7 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [3,5,4,2,4,6]
 * <p>
 * 输出：8
 * <p>
 * 解释：数组中的元素可以分为数对 (3,5)，(4,4) 和 (6,2) 。
 * <p>
 * 最大数对和为 max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == nums.length
 * <p>
 * 2 <= n <= 105
 * <p>
 * n是 偶数。
 * <p>
 * 1 <= nums[i] <= 105
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array
 */
public class Day_2021_07_20 {
    public static void main(String[] args) {
        System.out.println(minPairSum(new int[]{3, 5, 2, 3}));
        System.out.println(minPairSum(new int[]{3, 5, 4, 2, 4, 6}));
    }

    /**
     * 排序 + 遍历
     * <p>
     * 为了使得最终的结果是各种排列组合的情况里最小的那个，很直观可以想到一种贪心的思想：
     * <p>
     * 让每个数对和的值尽可能平均。这样，即使取这些数对和中的最大者，也不会很大.
     * <p>
     * 怎么使得数对和的值尽可能平均呢？
     * <p>
     * 那就是：
     * <p>
     * 先排序，然后第一个和倒数一个组合，第二个和倒数第二个组合、……，最中间的两个相组合
     * <p>
     * （因为题目保证 nums 的元素数量是偶数，所以不存在有元素会落单的情况）
     */
    public static int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n / 2; i++) {
            // 第i个与倒数第i个元素组合
            int sum = nums[i] + nums[n - i - 1];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
