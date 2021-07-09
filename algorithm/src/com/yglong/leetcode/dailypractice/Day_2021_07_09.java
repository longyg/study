package com.yglong.leetcode.dailypractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.10. 主要元素
 * <p>
 * <p>
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。
 * <p>
 * 请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：[1,2,5,9,5,9,5,5,5]
 * <p>
 * 输出：5
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：[3,2]
 * <p>
 * 输出：-1
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * <p>
 * 输出：2
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-majority-element-lcci
 */
public class Day_2021_07_09 {
    public static void main(String[] args) {
        System.out.println(majorityElement3(new int[]{3, 3, 4}));
    }

    /**
     * 哈希表
     * 时间复杂度：O(n)，空间复杂度：O(n)
     */
    public static int majorityElement(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
            if ((m.get(num) * 2) > n) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 排序法
     * 时间复杂度O(n) + 排序时间，空间复杂度O(1)
     */
    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int half = n / 2;
        int cur = nums[0];
        int c = 0; // 当前num的个数
        int i = 0;
        while (i < n) {
            if (nums[i] == cur) {
                c++;
            } else {
                cur = nums[i];
                c = 1;
            }
            if (c > half) { // 判断如果当前个数超过一半，返回结果
                return cur;
            }
            i++;
        }
        return -1;
    }

    /**
     * 投票算法
     * 时间复杂度O(n)，空间复杂度O(1)
     */
    public static int majorityElement3(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }

        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        return count * 2 > nums.length ? candidate : -1;
    }
}
