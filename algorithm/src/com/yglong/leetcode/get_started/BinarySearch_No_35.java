package com.yglong.leetcode.get_started;

/**
 * 35. 搜索插入位置
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 */
public class BinarySearch_No_35 {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));
        System.out.println(searchInsert(new int[]{2, 6, 7, 9, 11}, 1));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(searchInsert(new int[]{2, 6, 7, 9, 11}, 12));
        System.out.println(searchInsert(new int[]{2, 4, 5, 7}, 5));
        System.out.println(searchInsert(new int[]{2, 6, 7, 9, 11}, 9));
    }

    public static int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return low;
    }
}
