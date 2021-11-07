package com.yglong.leetcode.dailypractice._2021_11;

/**
 * 598. 范围求和 II
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/range-addition-ii/
 */
public class Day_2021_11_07 {
    public static void main(String[] args) {
        System.out.println(maxCount(3, 3, new int[][]{{2, 2}, {3, 3}}));
    }

    public static int maxCount(int m, int n, int[][] ops) {
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}
