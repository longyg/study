package com.yglong.leetcode.dailypractice._2021_08;

import java.util.*;

/**
 * 446. 等差数列划分 II - 子序列
 * <p>
 * <p>
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 * <p>
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * <p>
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * <p>
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * <p>
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 * <p>
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * <p>
 * 题目数据保证答案是一个 32-bit 整数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [2,4,6,8,10]
 * <p>
 * 输出：7
 * <p>
 * 解释：所有的等差子序列为：
 * <p>
 * [2,4,6]
 * <p>
 * [4,6,8]
 * <p>
 * [6,8,10]
 * <p>
 * [2,4,6,8]
 * <p>
 * [4,6,8,10]
 * <p>
 * [2,4,6,8,10]
 * <p>
 * [2,6,10]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [7,7,7,7,7]
 * <p>
 * 输出：16
 * <p>
 * 解释：数组中的任意子序列都是等差子序列。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 1000
 * <p>
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence
 */
public class Day_2021_08_11 {
    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10}));
        System.out.println(numberOfArithmeticSlices(new int[]{7, 7, 7, 7, 7}));
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 1, 2, 4, 1, 3, 10}));
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int ans = 0;
        Map<Long, Integer>[] f = new Map[n];
        for (int i = 0; i < n; i++) {
            f[i] = new HashMap<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; ++j) {
                long d = 1L * nums[i] - nums[j]; // 公差
                int cnt = f[j].getOrDefault(d, 0); // 前j个数中，公差为d的子序列个数
                ans += cnt;
                f[i].put(d, f[i].getOrDefault(d, 0) + cnt + 1); // 保存前i个数，公差为d的子序列个数
            }
        }
        return ans;
    }

}
