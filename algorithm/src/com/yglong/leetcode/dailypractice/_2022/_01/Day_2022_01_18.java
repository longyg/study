package com.yglong.leetcode.dailypractice._2022._01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 539. 最小时间差
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference/
 */
public class Day_2022_01_18 {
    public static void main(String[] args) {
        System.out.println(findMinDifference(Arrays.asList("00:00", "23:58", "00:01")));
    }

    public static int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int t0 = getMinutes(timePoints.get(0));
        int pre = t0;
        for (int i = 1; i < timePoints.size(); i++) {
            int t = getMinutes(timePoints.get(i));
            ans = Math.min(ans, t - pre);
            pre = t;
        }
        ans = Math.min(ans, t0 + 1440 - pre);
        return ans;
    }

    private static int getMinutes(String s) {
        return ((s.charAt(0) - '0') * 10 + (s.charAt(1) - '0')) * 60
                + (s.charAt(3) - '0') * 10 + s.charAt(4) - '0';
    }
}
