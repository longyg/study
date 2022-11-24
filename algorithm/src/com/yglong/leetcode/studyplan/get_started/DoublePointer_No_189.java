package com.yglong.leetcode.studyplan.get_started;

import java.util.Arrays;

/**
 * 189. 轮转数组
 * <p>
 * <p>
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/rotate-array/
 */
public class DoublePointer_No_189 {
    public static void main(String[] args) {
        int[] nums = new int[] {-1,-100,3,99};
        rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int tmp = nums[0];
        boolean[] moved = new boolean[n];
        for (int i = 0, index = 0; i < n; i++) {
            while (moved[index]) {
                index++;
                tmp = nums[index];
            }
            int next = (index + k) % n;
            int t = nums[next];
            nums[next] = tmp;
            tmp = t;
            moved[index] = true;
            index = next;
        }
    }
}
