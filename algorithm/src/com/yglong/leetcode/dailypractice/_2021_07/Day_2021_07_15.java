package com.yglong.leetcode.dailypractice._2021_07;

import java.util.Arrays;

/**
 * 1846. 减小和重新排列数组后的最大元素
 * <p>
 * <p>
 * 给你一个正整数数组arr。请你对 arr执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
 * <p>
 * <p>
 * arr中 第一个元素必须为1。
 * <p>
 * 任意相邻两个元素的差的绝对值 小于等于1，也就是说，对于任意的 1 <= i < arr.length（数组下标从 0 开始），
 * 都满足abs(arr[i] - arr[i - 1]) <= 1。abs(x)为x的绝对值。
 * <p>
 * <p>
 * 你可以执行以下 2 种操作任意次：
 * <p>
 * <p>
 * 减小 arr中任意元素的值，使其变为一个 更小的正整数。
 * <p>
 * 重新排列arr中的元素，你可以以任意顺序重新排列。
 * <p>
 * <p>
 * 请你返回执行以上操作后，在满足前文所述的条件下，arr中可能的 最大值。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：arr = [2,2,1,2,1]
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * <p>
 * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
 * <p>
 * arr 中最大元素为 2 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：arr = [100,1,1000]
 * <p>
 * 输出：3
 * <p>
 * 解释：
 * <p>
 * 一个可行的方案如下：
 * <p>
 * 1. 重新排列 arr 得到 [1,100,1000] 。
 * <p>
 * 2. 将第二个元素减小为 2 。
 * <p>
 * 3. 将第三个元素减小为 3 。
 * <p>
 * 现在 arr = [1,2,3] ，满足所有条件。
 * <p>
 * arr 中最大元素为 3 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：arr = [1,2,3,4,5]
 * <p>
 * 输出：5
 * <p>
 * 解释：数组已经满足所有条件，最大元素为 5 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging
 */
public class Day_2021_07_15 {
    public static void main(String[] args) {
        System.out.println(maximumElementAfterDecrementingAndRearranging2(new int[]{2, 2, 1, 2, 1}));
        System.out.println(maximumElementAfterDecrementingAndRearranging2(new int[]{100, 1, 1000}));
        System.out.println(maximumElementAfterDecrementingAndRearranging2(new int[]{1, 2, 3, 4, 5}));
        System.out.println(maximumElementAfterDecrementingAndRearranging2(new int[]{2, 8, 10, 9}));
    }

    /**
     * 排序后遍历
     * 时间复杂度为排序的时间复杂度o(nlogn)
     */
    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr); // 排序
        int i = 1;
        arr[0] = 1; // 初始化第一个元素必须为1
        while (i < arr.length) { // 从i=1遍历数组
            int max = arr[i - 1] + 1; // max为第i个元素最大可能值，最大只可能是上一个元素值加1
            if (max <= arr[i]) { // 如果原数组的第i个元素大于等于max，则设置第i个元素的值为max，否则保持不变
                arr[i] = max;
            }
            i++;
        }
        // 数组最后一个元素值即为结果
        return arr[i - 1];
    }

    /**
     * 计数后遍历
     * 时间复杂度o(n)
     */
    public static int maximumElementAfterDecrementingAndRearranging2(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int a : arr) {
            int i = Math.min(a, n);
            cnt[i] = cnt[i] + 1;
        }

        int miss = 0;
        for (int i = 1; i <= n; i++) {
            if (cnt[i] == 0) {
                miss++;
            } else {
                miss -= Math.min(cnt[i] - 1, miss);
            }
        }

        return n - miss;
    }
}
