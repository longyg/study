package com.yglong.leetcode.dailypractice._2021._12;

import java.util.HashMap;
import java.util.Map;

/**
 * 1995. 统计特殊四元组
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个 下标从 0 开始 的整数数组 nums ，返回满足下述条件的 不同 四元组 (a, b, c, d) 的 数目 ：
 * <p>
 * nums[a] + nums[b] + nums[c] == nums[d] ，且
 * <p>
 * a < b < c < d
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/count-special-quadruplets
 */
public class Day_2021_12_29 {
    public static void main(String[] args) {
        System.out.println(countQuadruplets(new int[]{1, 1, 1, 3, 5}));
    }

    /**
     * 暴力迭代
     */
    public static int countQuadruplets1(int[] nums) {
        int n = nums.length;

        int ans = 0;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }


    /**
     * 哈希表 存 nums[d]
     */
    public static int countQuadruplets2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> m = new HashMap<>();
        int ans = 0;
        for (int c = n - 2; c >= 2; c--) {
            m.put(nums[c + 1], m.getOrDefault(nums[c + 1], 0) + 1);
            for (int a = 0; a < c - 1; a++) {
                for (int b = a + 1; b < c; b++) {
                    ans += m.getOrDefault(nums[a] + nums[b] + nums[c], 0);
                }
            }
        }
        return ans;
    }

    /**
     * 哈希表 存 nums[d] - nums[c]
     */
    public static int countQuadruplets(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> m = new HashMap<>();
        int ans = 0;
        for (int b = n - 3; b >= 1; b--) {
            for (int d = b + 2; d < n; d++) {
                int g = nums[d] - nums[b + 1];
                m.put(g, m.getOrDefault(g, 0) + 1);
            }
            for (int a = 0; a < b; a++) {
                ans += m.getOrDefault(nums[a] + nums[b], 0);
            }
        }
        return ans;
    }
}
