package com.yglong.leetcode.dailypractice._2021._08;

/**
 * 1588. 所有奇数长度子数组的和
 * <p>
 * <p>
 * 给你一个正整数数组arr，请你计算所有可能的奇数长度子数组的和。
 * <p>
 * 子数组 定义为原数组中的一个连续子序列。
 * <p>
 * 请你返回 arr中 所有奇数长度子数组的和 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：arr = [1,4,2,5,3]
 * <p>
 * 输出：58
 * <p>
 * 解释：所有奇数长度子数组和它们的和为：
 * <p>
 * [1] = 1
 * <p>
 * [4] = 4
 * <p>
 * [2] = 2
 * <p>
 * [5] = 5
 * <p>
 * [3] = 3
 * <p>
 * [1,4,2] = 7
 * <p>
 * [4,2,5] = 11
 * <p>
 * [2,5,3] = 10
 * <p>
 * [1,4,2,5,3] = 15
 * <p>
 * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：arr = [1,2]
 * <p>
 * 输出：3
 * <p>
 * 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：arr = [10,11,12]
 * <p>
 * 输出：66
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= arr.length <= 100
 * <p>
 * 1 <= arr[i] <= 1000
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays
 */
public class Day_2021_08_29 {
    public static void main(String[] args) {
        System.out.println(sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
        System.out.println(sumOddLengthSubarrays(new int[]{1, 2}));
        System.out.println(sumOddLengthSubarrays(new int[]{10, 11, 12}));
    }

    /**
     * 前缀和（动态和）
     */
    public static int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;

        // 计算前缀和数组
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = arr[i - 1] + prefixSum[i - 1];
        }

        int k = n % 2 == 0 ? n - 1 : n;

        int sum = prefixSum[n]; // 初始化为单个元素的子数组总和
        // 从3个元素的子数组开始
        for (int i = 3; i <= k; i = i + 2) {
            int x = 0;
            while (x <= n - i) {
                sum += prefixSum[x + i] - prefixSum[x];
                x++;
            }
        }
        return sum;
    }
}
