package com.yglong.leetcode.dailypractice._2021._12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 689. 三个无重叠子数组的最大和
 * <p>
 * <p>
 * 难度：困难
 * <p>
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
 * <p>
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 */
public class Day_2021_12_08 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Day_2021_12_08()
                .maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2)));
        System.out.println(Arrays.toString(new Day_2021_12_08()
                .maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1}, 2)));
    }


    int[] nums;
    int k;
    Map<Integer, Integer> memo = new HashMap<>();
    int maxSum = 0;
    int[] ans = new int[3];

    /**
     * 暴力迭代法
     * <p>
     * 超出时间限制
     */
    public int[] maxSumOfThreeSubarrays1(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        int n = nums.length;
        for (int i = 0; i <= n - 3 * k; i++) {
            for (int j = i + k; j <= n - 2 * k; j++) {
                for (int l = j + k; l <= n - k; l++) {
                    int sum = getSum(i) + getSum(j) + getSum(l);
                    if (sum > maxSum) {
                        ans[0] = i;
                        ans[1] = j;
                        ans[2] = l;
                        maxSum = sum;
                    }
                }
            }
        }
        return ans;
    }

    private int getSum(int start) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        } else {
            int sum = 0;
            for (int i = start; i < start + k; i++) {
                sum += nums[i];
            }
            memo.put(start, sum);
            return sum;
        }
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];
        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
        int sum3 = 0, maxTotal = 0;
        for (int i = k * 2; i < nums.length; ++i) {
            sum1 += nums[i - k * 2];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= k * 3 - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - k * 3 + 1;
                }
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    maxSum12Idx1 = maxSum1Idx;
                    maxSum12Idx2 = i - k * 2 + 1;
                }
                if (maxSum12 + sum3 > maxTotal) {
                    maxTotal = maxSum12 + sum3;
                    ans[0] = maxSum12Idx1;
                    ans[1] = maxSum12Idx2;
                    ans[2] = i - k + 1;
                }
                sum1 -= nums[i - k * 3 + 1];
                sum2 -= nums[i - k * 2 + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return ans;
    }
}
