package com.yglong.leetcode.dailypractice._2021_06;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. 连续的子数组和
 * <p>
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * <p>
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * <p>
 * <p>
 * 做题思路：
 * <p>
 * 首先，注意审题，是连续子数组！连续，连续，连续子数组
 */
public class Day_2021_06_02 {
    public static void main(String[] args) {
        int[] nums = new int[]{23, 2, 6, 4, 7};
        System.out.println(checkSubarraySum(nums, 13));
    }

    /**
     * 根据 同余定理 实现
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < len; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    /**
     * 以下实现可以找出满足条件的非连续子数组
     */
    public static boolean checkSubarraySumWithoutSerial(int[] nums, int k) {
        for (int i = nums.length; i >= 2; i--) {
            if (checkSubarraySumForLen(nums, k, i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSubarraySumForLen(int[] nums, int k, int len) {
        for (int i = 0; i < nums.length; i++) {
            int tempSum = nums[i];
            int[] subArray = getSubArray(nums, i);
            if (checkSubArray(tempSum, subArray, k, len - 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSubArray(int sum, int[] arr, int k, int len) {
        if (sum % k == 0) {
            return true;
        }
        if (arr.length == 1) {
            return (sum + arr[0]) % k == 0;
        }
        for (int i = 0; i < arr.length; i++) {
            int tempSum = sum + arr[i];
            int[] subarr = getSubArray(arr, i);
            if (checkSubArray(tempSum, subarr, k, len - 1)) {
                return true;
            }
        }
        return false;
    }

    public static int[] getSubArray(int[] arr, int removeIndex) {
        int[] ret = new int[arr.length - 1];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != removeIndex) {
                ret[k++] = arr[i];
            }
        }
        return ret;
    }
}
