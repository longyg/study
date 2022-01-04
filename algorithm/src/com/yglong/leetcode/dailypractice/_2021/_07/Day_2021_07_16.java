package com.yglong.leetcode.dailypractice._2021._07;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * <p>
 * <p>
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * <p>
 * 输出: 2
 * <p>
 * <p>
 * 示例2:
 * <p>
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * <p>
 * 输出: 0
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 */
public class Day_2021_07_16 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{5, 7, 7, 8, 8, 10}, 8));
        System.out.println(search(new int[]{5, 7, 7, 8, 8, 10}, 5));
        System.out.println(search(new int[]{1}, 1));
        System.out.println(search(new int[]{1, 2, 2, 3, 3}, 2));
    }

    /**
     * 二分查找
     */
    public static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int low = 0, high = n - 1;
        int count = 0;
        // 如果数组头元素等于target，则依次向右查找，直到数组元素不等于target结束
        if (nums[low] == target) {
            count += search(nums, low, high, target, false) + 1;
            return count;
        }
        // 如果数组尾元素等于target，则依次向左查找，直到数组元素不等于target结束
        if (nums[high] == target) {
            count += search(nums, low, high, target, true) + 1;
            return count;
        }
        // 如果数组头和尾都不等于target，则二分查找
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 如果中间元素等于target，则分别向左和向右查找，直到数组元素不等于target结束
            if (nums[mid] == target) {
                // 向左查找
                count += search(nums, low, mid, target, true);
                // 向右查找
                count += search(nums, mid, high, target, false);
                return count + 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            }
        }
        return count;
    }

    /**
     * left: 表示是向左还是向右查找,true表示向左，false表示向右
     */
    private static int search(int[] nums, int low, int high, int target, boolean left) {
        // 向左查找
        int count = 0;
        while (low < high && nums[left ? --high : ++low] == target) {
            count++;
        }
        return count;
    }

}
