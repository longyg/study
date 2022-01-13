package com.yglong.leetcode.dailypractice._2022._01;

/**
 * 747. 至少是其他数字两倍的最大数
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
 * <p>
 * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others
 */
public class Day_2022_01_13 {
    public static void main(String[] args) {
        System.out.println(dominantIndex(new int[]{0, 0, 3, 2}));
        System.out.println(dominantIndex(new int[]{0, 1}));
        System.out.println(dominantIndex(new int[]{0, 0, 0, 1}));
        System.out.println(dominantIndex(new int[]{1, 2, 3, 6}));
        System.out.println(dominantIndex(new int[]{1}));
        System.out.println(dominantIndex(new int[]{1, 2, 6, 3}));
        System.out.println(dominantIndex(new int[]{6, 1, 2, 3}));
        System.out.println(dominantIndex(new int[]{1, 6, 2, 3}));
    }

    public static int dominantIndex(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int max = -1; // 最大数
        int second = -1; // 倒数第二大数
        int maxIndex = -1; // 最大数的下标
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                second = max; // 把第二大的数设置为之前的max
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }

        return (max >= second * 2) ? maxIndex : -1;
    }
}
