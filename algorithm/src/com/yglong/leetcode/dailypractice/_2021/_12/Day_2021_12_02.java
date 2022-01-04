package com.yglong.leetcode.dailypractice._2021._12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 506. 相对名次
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * <p>
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * <p>
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * <p>
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * <p>
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * <p>
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/relative-ranks/
 */
public class Day_2021_12_02 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRelativeRanks(new int[]{10, 3, 8, 9, 4})));
    }

    /**
     * 排序 + 哈希表
     */
    public static String[] findRelativeRanks(int[] score) {
        int n = score.length;
        int[] sorted = Arrays.copyOf(score, n);
        Arrays.sort(sorted);
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int i = n - 1, rank = 1; i >= 0; i--, rank++) {
            rankMap.put(sorted[i], rank);
        }
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            int rank = rankMap.get(score[i]);
            if (rank == 1) {
                ans[i] = "Gold Medal";
            } else if (rank == 2) {
                ans[i] = "Silver Medal";
            } else if (rank == 3) {
                ans[i] = "Bronze Medal";
            } else {
                ans[i] = Integer.toString(rank);
            }
        }
        return ans;
    }
}
