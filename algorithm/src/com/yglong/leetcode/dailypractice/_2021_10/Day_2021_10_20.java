package com.yglong.leetcode.dailypractice._2021_10;

import java.util.Arrays;

/**
 * 453. 最小操作次数使数组元素相等
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/
 */
public class Day_2021_10_20 {

    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{5, 6, 8, 8, 5}));
        System.out.println(minMoves(new int[]{1, 2, 3}));
        System.out.println(minMoves(new int[]{1, 1, 1}));
        System.out.println(minMoves(new int[]{1, 2, 3, 4}));
        System.out.println(minMoves(new int[]{1, 1, 1000000000}));
    }

    /**
     * 数学推理
     */
    public static int minMoves(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        int steps = 0;
        for (int num : nums) {
            steps += num - min;
        }
        return steps;
    }

    /**
     * 递归
     */
    public static int minMoves1(int[] nums) {
        int steps = 0;
        return minMoveSteps(nums, steps);
    }

    private static int minMoveSteps(int[] nums, int steps) {
        int max = 0, min = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
            if (nums[i] < nums[min]) {
                min = i;
            }
        }
        if (max == min) return steps;
        int step = nums[max] - nums[min];
        steps += step;
        for (int i = 0; i < nums.length; i++) {
            if (i != max) {
                nums[i] += step;
            }
        }
        return minMoveSteps(nums, steps);
    }
}
