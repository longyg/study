package com.yglong.leetcode.dailypractice._2021._08;

import java.util.Arrays;

/**
 * 581. 最短无序连续子数组
 * <p>
 * <p>
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [2,6,4,8,10,9,15]
 * <p>
 * 输出：5
 * <p>
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1,2,3,4]
 * <p>
 * 输出：0
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [1]
 * <p>
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 104
 * <p>
 * -105 <= nums[i] <= 105
 * <p>
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 */
public class Day_2021_08_03 {
    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray2(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(findUnsortedSubarray2(new int[]{1, 2, 3, 4}));
        System.out.println(findUnsortedSubarray2(new int[]{1}));
    }

    /**
     * 排序 + 迭代
     * 时间复杂度为排序的时间复杂度: O(nlogn)
     */
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        if (isSorted(nums)) {
            return 0;
        }
        // 复制原数组并排序
        int[] sorted = Arrays.copyOf(nums, n);
        Arrays.sort(sorted);

        int start = 0;
        // 从左往右找，找到第一个不同的作为start
        while (sorted[start] == nums[start]) {
            start++;
        }
        int end = n - 1;
        // 从右往左找，找到第一不同的作为end
        while (sorted[end] == nums[end]) {
            end--;
        }
        return end - start + 1;
    }

    private static boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 官方解法
     * O(n)时间复杂度
     */
    public static int findUnsortedSubarray2(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
