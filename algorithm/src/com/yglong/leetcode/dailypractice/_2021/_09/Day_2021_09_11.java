package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 600. 不含连续1的非负整数
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/
 */
public class Day_2021_09_11 {
    public static void main(String[] args) {
        System.out.println(findIntegers(5));
        System.out.println(findIntegers(100000000));
    }

    public static int findIntegers1(int n) {
        int cnt = 0;
        for (int i = 0; i <= n; i++) {
            String s = Integer.toBinaryString(i);
            if (!s.contains("11")) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 动态规划
     */
    public static int findIntegers(int n) {
        if (n == 0) return 1;

        int[][] dp = new int[50][2];
        dp[1][0] = 1;
        dp[1][1] = 2;
        for (int i = 1; i < 49; i++) {
            dp[i + 1][0] = dp[i][1];
            dp[i + 1][1] = dp[i][0] + dp[i][1];
        }

        int len = 0;
        for (int i = 31; i >= 0; i--) {
            if (((n >> i) & 1) == 1) {
                len = i;
                break;
            }
        }

        int ans = 0, prev = 0;
        for (int i = len; i >= 0; i--) {
            // 当前位是 0 还是 1
            int cur = ((n >> i) & 1);
            // 如果当前位是 1，那么填 0 的话，后面随便填都符合，将方案数累加
            if (cur == 1) ans += dp[i + 1][0];
            // 出现连续位为 1，方案数被计算完了
            if (prev == 1 && cur == 1) break;
            prev = cur;
            if (i == 0) ans++;
        }
        return ans;
    }
}
