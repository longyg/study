package com.yglong.leetcode.dailypractice._2021._11;

/**
 * 375. 猜数字大小 II
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
 */
public class Day_2021_11_12 {
    public static void main(String[] args) {
        System.out.println(new Day_2021_11_12().getMoneyAmount(10));
    }

    /**
     * 递归
     */
    int N = 210;
    int[][] cache = new int[N][N];

    public int getMoneyAmount(int n) {
        return dfs(1, n);
    }

    private int dfs(int l, int r) {
        if (l >= r) return 0;
        if (cache[l][r] != 0) return cache[l][r];
        int ans = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            int cur = Math.max(dfs(l, i - 1), dfs(i + 1, r)) + i;
            ans = Math.min(ans, cur);
        }
        cache[l][r] = ans;
        return ans;
    }
}
