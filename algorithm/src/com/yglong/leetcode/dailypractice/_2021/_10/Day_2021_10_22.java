package com.yglong.leetcode.dailypractice._2021._10;

import java.util.*;

/**
 * 229. 求众数 II
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/majority-element-ii/
 */
public class Day_2021_10_22 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(majorityElement(new int[]{3, 2, 3}).toArray()));
        System.out.println(Arrays.toString(majorityElement(new int[]{1}).toArray()));
        System.out.println(Arrays.toString(majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2}).toArray()));
    }


    /**
     * 哈希表
     * <p>
     * 时间复杂度：O(n), 空间复杂度：O(n)
     */
    public static List<Integer> majorityElement1(int[] nums) {
        // 哈希表记录每个整数出现的次数
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }
        int n = nums.length / 3 + 1;
        List<Integer> ans = new ArrayList<>();
        // 将出现次数大于 n/3 的整数挑选出来
        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            if (entry.getValue() >= n) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }

    /**
     * 优化方法： 摩尔投票
     * <p>
     * 出现次数超过 n/3 的整数, 最多可能只会有2个整数
     * <p>
     * 时间复杂度：O(n), 空间复杂度：O(1)
     */
    public static List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, candidate2 = 0, vote1 = 0, vote2 = 0;

        // 投票
        for (int num : nums) {
            if (vote1 > 0 && num == candidate1) {
                vote1++;
            } else if (vote2 > 0 && num == candidate2) {
                vote2++;
            } else if (vote1 == 0) {
                candidate1 = num;
                vote1++;
            } else if (vote2 == 0) {
                candidate2 = num;
                vote2++;
            } else {
                vote1--;
                vote2--;
            }
        }

        // 计算candidate的总count
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == candidate1) {
                cnt1++;
            }
            if (vote2 > 0 && num == candidate2) {
                cnt2++;
            }
        }

        // 检查两个candidate的出现次数是否满足大于 N/3 的要求
        List<Integer> ans = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            ans.add(candidate1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            ans.add(candidate2);
        }
        return ans;
    }
}
