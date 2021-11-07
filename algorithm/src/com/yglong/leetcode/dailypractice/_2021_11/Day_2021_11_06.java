package com.yglong.leetcode.dailypractice._2021_11;

/**
 * 268. 丢失的数字
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/missing-number/
 */
public class Day_2021_11_06 {
    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{3, 0, 1}));
    }

    public static int missingNumber(int[] nums) {
        int n = nums.length;

        int t = 0;
        for (int i = 1; i <= n; i++) {
            t += i;
        }

        for (int num : nums) {
            t -= num;
        }
        return t;
    }
}
