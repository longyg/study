package com.yglong.leetcode.dailypractice;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * <p>
 * 输入: nums = [0,1,0]
 * <p>
 * 输出: 2
 * <p>
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数
 * <p>
 * 提示：
 * <p>
 * - 1 <= nums.length <= 105
 * <p>
 * - nums[i] 不是 0 就是 1
 */
public class Day_2021_06_03 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1};
        System.out.println(findMaxLength(nums));
    }

    public static int findMaxLength(int[] nums) {
        return findMaxLengthQuickly(nums);
    }

    /**
     * 利用前缀和
     */
    public static int findMaxLengthQuickly(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        // 用于存储每个前缀和第一次出现的下标
        // key为前缀和，value为下标
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prevSum = 0;
        int maxLength = 0;
        for (int i = 0; i < len; i++) {
            prevSum += (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(prevSum)) {
                int prevIndex = map.get(prevSum);
                int length = i - prevIndex;
                if (length > maxLength) {
                    maxLength = length;
                }
            } else {
                map.put(prevSum, i);
            }
        }
        return maxLength;
    }


    /**
     * 暴力法
     */
    public static int findMaxLengthExhaustively(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        for (int i = len; i > 1; i--) {
            if (isEqual(nums, i)) {
                return i;
            }
        }
        return 0;
    }

    public static boolean isEqual(int[] nums, int len) {
        if (len % 2 != 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + len - 1; j < nums.length; j++) {
                if (isEqual(nums, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isEqual(int[] nums, int startIndex, int endIndex) {
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
        }
        return zeroCount == oneCount;
    }
}
