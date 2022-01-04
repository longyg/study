package com.yglong.leetcode.dailypractice._2021._10;

/**
 * 剑指 Offer II 069. 山峰数组的顶部
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/B1IidL/
 */
public class Day_2021_10_14 {

    public static void main(String[] args) {
        System.out.println(peakIndexInMountainArray(new int[]{0, 1, 0}));
        System.out.println(peakIndexInMountainArray(new int[]{1, 3, 5, 4, 2}));
        System.out.println(peakIndexInMountainArray(new int[]{0, 10, 5, 2}));
        System.out.println(peakIndexInMountainArray(new int[]{3, 4, 5, 1}));
        System.out.println(peakIndexInMountainArray(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19}));
        System.out.println(peakIndexInMountainArray(new int[]{18, 29, 38, 59, 98, 100, 99, 98, 90}));
    }

    /**
     * 枚举
     */
    public static int peakIndexInMountainArray1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return i - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int low = 1, high = arr.length - 1;
        while (low <= high) {
            int mid = low + high >> 1;
            if (arr[mid] < arr[mid - 1]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
}
