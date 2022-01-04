package com.yglong.leetcode.dailypractice._2021._12;

import java.util.Arrays;

/**
 * 475. 供暖器
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * <p>
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * <p>
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * <p>
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/heaters
 */
public class Day_2021_12_20 {
    public static void main(String[] args) {
        System.out.println(findRadius(new int[]{1, 2, 3}, new int[]{2}));
        System.out.println(findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
        System.out.println(findRadius(new int[]{1, 5}, new int[]{2}));
    }

    /**
     * 迭代法
     */
    public static int findRadius1(int[] houses, int[] heaters) {
        int m = houses.length;
        int n = heaters.length;
        int[] minDis = new int[m]; // 每个房间与所有供暖器的最小距离
        for (int i = 0; i < m; i++) {
            minDis[i] = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                minDis[i] = Math.min(minDis[i], Math.abs(houses[i] - heaters[j]));
            }
        }
        return Arrays.stream(minDis).max().getAsInt();
    }

    /**
     * 排序 + 双指针
     */
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {
            // 初始化最小距离为与第一个取暖器的距离
            int minDis = Math.abs(houses[i] - heaters[j]);
            while (j < heaters.length - 1) {
                // 计算与下一个取暖器的距离
                int nextDis = Math.abs(houses[i] - heaters[j + 1]);
                // 如果最小距离小于与下一个取暖器的距离，则不再继续计算与剩余的取暖器的距离
                // 因为此时最小值已经找到
                if (minDis < nextDis) break;
                minDis = nextDis;
                j++;
            }
            ans = Math.max(ans, minDis);
        }
        return ans;
    }
}
