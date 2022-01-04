package com.yglong.leetcode.dailypractice._2021._07;

import java.util.Arrays;

/**
 * 1818. 绝对差值和
 * <p>
 * <p>
 * 给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
 * <p>
 * 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
 * <p>
 * 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
 * <p>
 * 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 10^9 + 7 取余 后返回。
 * <p>
 * <p>
 * |x| 定义为：
 * <p>
 * 如果 x >= 0 ，值为 x ，或者
 * <p>
 * 如果 x <= 0 ，值为 -x
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums1 = [1,7,5], nums2 = [2,3,5]
 * <p>
 * 输出：3
 * <p>
 * 解释：有两种可能的最优方案：
 * <p>
 * - 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
 * <p>
 * - 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
 * <p>
 * 两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
 * <p>
 * 输出：0
 * <p>
 * 解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
 * <p>
 * 输出：20
 * <p>
 * 解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
 * <p>
 * 绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-sum-difference
 */
public class Day_2021_07_14 {

    public static void main(String[] args) {
        System.out.println(minAbsoluteSumDiff2(new int[]{1, 10, 4, 4, 2, 7}, new int[]{9, 3, 5, 1, 7, 4}));
        System.out.println(minAbsoluteSumDiff2(new int[]{2, 4, 6, 8, 10}, new int[]{2, 4, 6, 8, 10}));
        System.out.println(minAbsoluteSumDiff2(new int[]{1, 7, 5}, new int[]{2, 3, 5}));
        System.out.println(minAbsoluteSumDiff2(
                new int[]{86, 27, 43, 69, 74, 75, 43, 62, 90, 37, 39, 94, 64, 55, 59, 8, 7, 39, 43,
                        81, 22, 19, 50, 30, 63, 15, 38, 30, 61, 50, 69, 34, 83, 9, 87, 83, 14, 6, 41,
                        64, 38, 75, 23, 32, 49, 89, 15, 97, 23, 49, 20, 36, 85, 58, 37, 10, 39, 69, 11,
                        62, 30, 16, 2, 96, 98, 84, 29, 68, 64, 42, 29, 81, 45, 65, 58, 47, 89, 55, 10,
                        97, 30, 56, 8, 71, 71, 74, 18, 61, 25, 80, 95, 11, 45, 14, 58, 27, 35, 22, 57, 6},
                new int[]{13, 32, 9, 62, 52, 36, 42, 16, 8, 56, 52, 69, 52, 28, 18, 60, 59, 66, 73, 87,
                        97, 31, 13, 22, 42, 92, 70, 73, 68, 62, 11, 25, 68, 79, 78, 100, 48, 66, 6, 81, 76,
                        47, 12, 80, 20, 84, 91, 100, 68, 61, 47, 3, 21, 77, 58, 73, 33, 55, 58, 61, 6, 26, 28,
                        47, 79, 61, 45, 77, 18, 20, 15, 46, 65, 73, 29, 65, 45, 100, 80, 35, 54, 43, 14, 33,
                        81, 2, 72, 52, 20, 9, 55, 73, 90, 41, 78, 32, 36, 27, 13, 89}));
        System.out.println(minAbsoluteSumDiff2(new int[]{5, 2, 7}, new int[]{10, 7, 12}));
    }

    /**
     * 暴力法
     * 时间复杂度O(n^3)
     */
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int sum = getSumDiff(nums1, nums2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = nums1[i];
                nums1[i] = nums1[j];
                int sumDiff = getSumDiff(nums1, nums2);
                if (sumDiff < sum) {
                    sum = sumDiff;
                }
                nums1[i] = tmp; //恢复原数组
            }
        }
        return sum;
    }

    private static int getSumDiff(int[] nums1, int[] nums2) {
        int MOD = 1000000000 + 7;
        int sum = 0;
        for (int i = 0; i < nums1.length; i++) {
            sum += (Math.abs(nums1[i] - nums2[i])) % MOD;
        }
        return sum;
    }

    /**
     * 排序 + 二分查找
     * 时间复杂度O(nlogn)
     */
    public static int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {
        int MOD = 1000000000 + 7;
        int n = nums1.length;
        int[] nums = Arrays.copyOf(nums1, n);
        Arrays.sort(nums);
        int sum = 0, maxDiff = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            int j = binarySearch(nums, nums2[i]);
            if (j < n) {
                maxDiff = Math.max(maxDiff, diff - (nums[j] - nums2[i]));
            }
            if (j > 0) {
                maxDiff = Math.max(maxDiff, diff - (nums2[i] - nums[j - 1]));
            }
        }
        return (sum - maxDiff + MOD) % MOD;
    }

    private static int binarySearch(int[] nums, int num) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        if (nums[high] < num) {
            return high + 1;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < num) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
