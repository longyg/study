package com.yglong.leetcode.dailypractice._2021._09;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs/
 */
public class Day_2021_09_13 {

    public static void main(String[] args) {
        System.out.println(numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
        System.out.println(numberOfBoomerangs(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }


    public static int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return 0;
        }

        int ans = 0;

        // 记录当前点与其他距离相同的点的个数
        Map<Double, Integer> m = new HashMap<>();
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                double d = distance(p1, p2);
                m.put(d, m.getOrDefault(d, 0) + 1);
            }
            for (int cnt : m.values()) {
                ans += (cnt * (cnt - 1));
            }
            m.clear();
        }

        return ans;
    }

    private static double distance(int[] p1, int[] p2) {
        return Math.pow(p1[0] - p2[0], 2) + +Math.pow(p1[1] - p2[1], 2);
    }
}
