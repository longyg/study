package com.yglong.leetcode.dailypractice._2021_06;

/**
 * 852. 山脉数组的峰顶索引
 * <p>
 * <p>
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * <p>
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * <p>
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * <p>
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * <p>
 * <p>
 * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：arr = [0,10,5,2]
 * <p>
 * 输出：1
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
 */
public class Day_2021_06_15 {
    public static void main(String[] args) {
        System.out.println(peakIndexInMountainArray2(new int[]{0, 10, 5, 2}));
    }

    /**
     * O(n)
     */
    public static int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return i - 1;
            }
        }
        return -1;
    }

    /**
     * 二分法，事件复杂度O(log(n))
     */
    public static int peakIndexInMountainArray2(int[] arr) {
        return peak(arr, 0, arr.length);
    }

    private static int peak(int[] arr, int start, int end) {
        if (end - start == 1) {
            return arr[end] > arr[start] ? end : start;
        }
        int mid = start + ((end - start) / 2);
        if (arr[mid] > arr[mid - 1]) {
            return peak(arr, mid, end);
        } else {
            return peak(arr, start, mid);
        }
    }
}
