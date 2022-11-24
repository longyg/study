package com.yglong.leetcode.studyplan.get_started;

import java.util.Arrays;

/**
 * 977. 有序数组的平方
 * <p>
 * <p>
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 */
public class DoublePointer_No_977 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4, -1, 0, 3, 10})));
        System.out.println(Arrays.toString(sortedSquares(new int[]{-7, -3, 2, 3, 11})));
        System.out.println(Arrays.toString(sortedSquares(new int[]{-5, -3, -2, -1})));
        System.out.println(Arrays.toString(sortedSquares(new int[]{1, 2, 3, 4})));
    }

    /**
     * 双指针
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int index = 0;
        while (index < n && nums[index] < 0) {
            index++;
        }
        int l = index - 1, r = index;
        int i = 0;
        int[] ans = new int[n];
        while (l >= 0 || r < n) {
            if (l < 0) {
                ans[i] = nums[r] * nums[r];
                r++;
            } else if (r > n - 1) {
                ans[i] = nums[l] * nums[l];
                l--;
            } else {
                if (Math.abs(nums[l]) > nums[r]) {
                    ans[i] = nums[r] * nums[r];
                    r++;
                } else {
                    ans[i] = nums[l] * nums[l];
                    l--;
                }
            }
            i++;
        }
        return ans;
    }
}
