package com.yglong.leetcode.dailypractice._2021._07;

import java.util.HashMap;
import java.util.Map;

/**
 * 1713. 得到子序列的最少操作次数
 * <p>
 * <p>
 * 给你一个数组target，包含若干 互不相同的整数，以及另一个整数数组arr，arr可能 包含重复元素。
 * <p>
 * 每一次操作中，你可以在 arr的任意位置插入任一整数。比方说，如果arr = [1,4,1,2]，那么你可以在中间添加 3得到[1,4,3,1,2]。
 * <p>
 * 你可以在数组最开始或最后面添加整数。
 * <p>
 * 请你返回 最少操作次数，使得target成为arr的一个子序列。
 * <p>
 * 一个数组的 子序列指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。
 * <p>
 * 比方说，[2,7,4]是[4,2,3,7,2,1,4]的子序列（加粗元素），但[2,4,2]不是子序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：target = [5,1,3], arr = [9,4,2,3,4]
 * <p>
 * 输出：2
 * <p>
 * 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
 * <p>
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= target.length, arr.length <= 105
 * <p>
 * 1 <= target[i], arr[i] <= 109
 * <p>
 * target不包含任何重复元素。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence
 */
public class Day_2021_07_26 {
    public static void main(String[] args) {
        System.out.println(minOperations2(new int[]{5, 1, 3}, new int[]{9, 4, 2, 3, 4}));
        System.out.println(minOperations2(new int[]{6, 4, 8, 1, 3, 2}, new int[]{4, 7, 6, 2, 3, 8, 6, 1}));
        System.out.println(minOperations2(new int[]{16, 7, 20, 11, 15, 13, 10, 14, 6, 8},
                new int[]{11, 14, 15, 7, 5, 5, 6, 10, 11, 6}));
        System.out.println(minOperations2(new int[]{5, 10, 8, 11, 3, 15, 9, 20, 18, 13},
                new int[]{15, 8, 2, 9, 11, 20, 8, 11, 7, 2}));
        System.out.println(minOperations2(new int[]{1, 3, 8}, new int[]{2, 6}));
    }

    /**
     * 动态规划求最长公共子序列
     * 找到target与arr的最长公共子序列，最少插入的个数就等于target的长度减去最长公共子序列的长度
     */
    public static int minOperations(int[] target, int[] arr) {
        int n = target.length;
        int k = arr.length;
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            int a = arr[i - 1];
            for (int j = 1; j <= n; j++) {
                int t = target[j - 1];
                if (a == t) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return n - dp[k][n];
    }

    /**
     * 本题可以先把问题转化为找最长递增子序列
     * 然后利用贪心 + 二分查找找出最长递增子序列
     */
    public static int minOperations2(int[] target, int[] arr) {
        int arrLen = arr.length;
        int targetLen = target.length;
        int[] nums = new int[arrLen];
        int iLen = 0;

        // 保存target中每个元素的索引
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < targetLen; i++) {
            m.put(target[i], i);
        }

        // 将数组arr转换为出现在target数组的索引位置的数组
        // 例如当：target = {6, 4, 8, 1, 3, 2}, arr = {4, 7, 6, 2, 3, 8, 6, 1}
        // 得到nums数组为 {1, 0, 5, 4, 2, 0, 3}
        // nums中第一个元素值1，表示arr的第1个元素4在target数组中的索引，即为1
        // nums中第一个元素值0，表示arr的第3个元素6在target数组中的索引，即为0
        // 注意，由于arr的第2个元素7不在target数组中，因此在nums中忽略它
        for (int value : arr) {
            // 这里使用map是为了避免双重循环，时间超时
            if (m.containsKey(value)) {
                nums[iLen++] = m.get(value);
            }
        }

        if (iLen < 1) {
            return targetLen;
        }

        // 求nums数组的最长递增子序列的长度
        int n = iLen;
        int len = 1; // 最长递增子序列的长度
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int k = binarySearch(d, nums[i], len);
                d[k + 1] = nums[i];
            }
        }
        return targetLen - len;
    }

    private static int binarySearch(int[] d, int num, int high) {
        int low = 1;
        int pos = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (d[mid] < num) {
                pos = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return pos;
    }
}
