package com.yglong.leetcode.dailypractice._2022._01;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素 II
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 */
public class Day_2022_01_19 {
    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2}, 2));
    }

    /**
     * 迭代
     */
    public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j <= i + k && j < n; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 哈希表
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (m.containsKey(num) && (i - m.get(num) <= k)) {
                return true;
            }
            m.put(num, i);
        }
        return false;
    }
}
