package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 704. 二分查找
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-search/
 */
public class Day_2021_09_06 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 13));
    }

    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
