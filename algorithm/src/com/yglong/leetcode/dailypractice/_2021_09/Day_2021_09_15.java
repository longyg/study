package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 162. 寻找峰值
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-peak-element/
 */
public class Day_2021_09_15 {
    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1})); // 2
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4})); // 1 或 5
        System.out.println(findPeakElement(new int[]{1}));  // 0
        System.out.println(findPeakElement(new int[]{2, 1})); // 0
        System.out.println(findPeakElement(new int[]{1, 2})); // 1
    }

    /**
     * 拷贝并填充数组
     */
    public static int findPeakElement1(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = Integer.MIN_VALUE;
        arr[n + 1] = Integer.MIN_VALUE;
        System.arraycopy(nums, 0, arr, 1, n);

        for (int i = 1; i <= n; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i - 1;
            }
        }

        return 0;
    }


    /**
     * 遍历
     */
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int prev = i == 0 ? Integer.MIN_VALUE : nums[i - 1];
            int next = i == n - 1 ? Integer.MIN_VALUE : nums[i + 1];
            if (nums[i] > prev && nums[i] > next) {
                return i;
            } else if (nums[i] > next) {
                // 如果当前元素比下一个元素大，则可以跳过下一个元素，因为它肯定不是峰值
                i++;
            }
        }
        return 0;
    }
}
