package com.yglong.leetcode.studyplan.get_started;

import java.util.Arrays;

/**
 * 283. 移动零
 * <p>
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/move-zeroes/
 */
public class DoublePointer_No_283 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 1};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = 0; i < n && j < n; j++) {
            if (nums[j] == 0) continue;
            if (i != j) {
                nums[i] = nums[j];
                nums[j] = 0;
            }
            i++;
        }
    }
}
