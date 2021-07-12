package com.yglong.leetcode.dailypractice._2021_07;

import java.util.HashMap;
import java.util.Map;

/**
 * 930. 和相同的二元子数组
 * <p>
 * <p>
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * <p>
 * 子数组 是数组的一段连续部分。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,0,1,0,1], goal = 2
 * <p>
 * 输出：4
 * <p>
 * 解释：
 * <p>
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * <p>
 * [1,0,1,0,1]
 * <p>
 * [1,0,1,0,1]
 * <p>
 * [1,0,1,0,1]
 * <p>
 * [1,0,1,0,1]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [0,0,0,0,0], goal = 0
 * <p>
 * 输出：15
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * <p>
 * nums[i] 不是 0 就是 1
 * <p>
 * 0 <= goal <= nums.length
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
 */
public class Day_2021_07_08 {

    public static void main(String[] args) {
        System.out.println(numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        System.out.println(numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
        System.out.println(numSubarraysWithSum(new int[]{0, 1, 1, 1, 1}, 3));
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }
}
