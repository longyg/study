package com.yglong.leetcode.dailypractice._2021._10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 260. 只出现一次的数字 III
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/single-number-iii/
 */
public class Day_2021_10_30 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
        System.out.println(Arrays.toString(singleNumber(new int[]{-1, 0})));
        System.out.println(Arrays.toString(singleNumber(new int[]{0, 1})));
    }

    /**
     * 哈希表
     */
    public static int[] singleNumber(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            if (entry.getValue() == 1) {
                ans[i] = entry.getKey();
                i++;
            }
        }
        return ans;
    }
}
