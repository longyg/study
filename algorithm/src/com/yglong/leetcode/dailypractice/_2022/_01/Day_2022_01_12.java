package com.yglong.leetcode.dailypractice._2022._01;

/**
 * 334. 递增的三元子序列
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
 */
public class Day_2022_01_12 {
    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{5, 1, 5, 5, 2, 5, 4}));
        System.out.println(increasingTriplet(new int[]{1, 1, -2, 6}));
        System.out.println(increasingTriplet(new int[]{20, 100, 10, 12, 5, 13}));
        System.out.println(increasingTriplet(new int[]{6, 7, 1, 2}));
        System.out.println(increasingTriplet(new int[]{1, 5, 0, 4, 1, 3}));
        System.out.println(increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
        System.out.println(increasingTriplet(new int[]{5, 4, 3, 2, 1}));
    }

    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int first = nums[0]; // 递增子序列的第一个元素
        int i = 1;
        while (i < n) {
            if (nums[i] > first) break;
            first = nums[i];
            i++;
        }

        // 如果已经到数组最后了，则不存在三元组，返回false
        if (i >= n) return false;

        int second = nums[i]; // 递增子序列的第二个元素

        // 记录比已找到的二元组的first更小的数
        boolean hasNewFirst = false;
        int newFirst = 0;
        for (int j = i; j < n; j++) {
            // 如果下一个元素大于已找到的第二个元素，则存在三元组，返回true
            if (nums[j] > second) return true;
            // 如果下一个元素比已找到的第二个元素小，且比第一个元素大，则替换第二个元素
            if (nums[j] < second && nums[j] > first) second = nums[j];

            if (hasNewFirst) {
                if (nums[j] < newFirst) {
                    newFirst = nums[j];
                    hasNewFirst = true;
                } else {
                    // 替换已找到的二元组
                    first = newFirst;
                    second = nums[j];
                    hasNewFirst = false;
                }
            } else {
                if (nums[j] < first) { // 如果比已存在的二元组的first更小，则记录下来
                    newFirst = nums[j];
                    hasNewFirst = true;
                }
            }
        }
        return false;
    }
}
