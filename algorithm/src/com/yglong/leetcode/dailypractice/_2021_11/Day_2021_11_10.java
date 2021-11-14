package com.yglong.leetcode.dailypractice._2021_11;

/**
 * 495. 提莫攻击
 * <p>
 * 难度：简单
 * <p>
 * 连接：https://leetcode-cn.com/problems/teemo-attacking/
 */
public class Day_2021_11_10 {
    public static void main(String[] args) {
        System.out.println(findPoisonedDuration(new int[]{1, 4}, 2));
        System.out.println(findPoisonedDuration(new int[]{1, 2}, 2));
        System.out.println(findPoisonedDuration(new int[]{1, 2, 3, 4, 5}, 5));
    }

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        int total = duration;
        for (int i = 1; i < timeSeries.length; i++) {
            total += Math.min(timeSeries[i] - timeSeries[i - 1], duration);
        }
        return total;
    }
}
