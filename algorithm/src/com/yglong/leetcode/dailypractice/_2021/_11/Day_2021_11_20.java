package com.yglong.leetcode.dailypractice._2021._11;

import java.util.HashMap;
import java.util.Map;

/**
 * 594. 最长和谐子序列
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-harmonious-subsequence/
 */
public class Day_2021_11_20 {

    public static void main(String[] args) {
        System.out.println(findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
    }

    /**
     * 哈希表
     */
    public static int findLHS(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        int maxLen = 0;
        for (Integer num : m.keySet()) {
            if (m.containsKey(num + 1)) {
                maxLen = Math.max(maxLen, (m.get(num) + m.get(num + 1)));
            }
        }
        return maxLen;
    }
}
