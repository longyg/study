package com.yglong.leetcode.dailypractice;

import java.util.Arrays;

/**
 * 1833. 雪糕的最大数量
 * <p>
 * <p>
 * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
 * <p>
 * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
 * <p>
 * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
 * <p>
 * 注意：Tony 可以按任意顺序购买雪糕。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：costs = [1,3,2,4,1], coins = 7
 * <p>
 * 输出：4
 * <p>
 * 解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars
 */
public class Day_2021_07_02 {
    public static void main(String[] args) {
        System.out.println(6 == maxIceCream2(new int[]{1, 6, 3, 1, 2, 5}, 20));
        System.out.println(0 == maxIceCream2(new int[]{10, 6, 8, 7, 7, 8}, 5));
        System.out.println(maxIceCream2(new int[]{1, 3, 2, 4, 1}, 7));
        System.out.println(9 == maxIceCream2(new int[]{7, 3, 3, 6, 6, 6, 10, 5, 9, 2}, 56));
        System.out.println(maxIceCream2(new int[]{27, 23, 33, 26, 46, 86, 70, 85, 89, 82, 57, 66, 42,
                18, 18, 5, 46, 56, 42, 82, 52, 78, 4, 27, 96, 74, 74, 52, 2, 24, 78, 18, 42, 10, 12,
                10, 80, 30, 60, 38, 32, 7, 98, 26, 18, 62, 50, 42, 15, 14, 32, 86, 93, 98, 47, 46,
                58, 42, 74, 69, 51, 53, 58, 40, 66, 46, 65, 2, 10, 82, 94, 26, 6, 78, 2, 101, 97,
                16, 12, 18, 71, 5, 46, 22, 58, 12, 18, 62, 61, 51, 2, 18, 34, 12, 36, 58, 20, 12, 17, 70}, 241));
    }

    /**
     * 动态规划
     * 可以解，但是二维DP，空间复杂度超过了限制
     */
    public static int maxIceCream(int[] costs, int coins) {
        int[][] dp = new int[costs.length][coins];

        int oneIndex = -1;
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] == 1) {
                oneIndex = i;
                break;
            }
        }
        for (int i = 0; i < costs.length; i++) {
            if (oneIndex >= 0 && i >= oneIndex) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }

        for (int j = 0; j < coins; j++) {
            if (j + 1 >= costs[0]) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = 0;
            }
        }

        for (int i = 1; i < costs.length; i++) {
            for (int j = 1; j < coins; j++) {
                if (dp[i - 1][j] == 0) {
                    if (j + 1 >= costs[i]) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (j + 1 <= costs[i]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], 1 + dp[i - 1][j - costs[i]]);
                    }
                }
            }
        }
        return dp[costs.length - 1][coins - 1];
    }

    /**
     * 排序+贪心算法
     */
    public static int maxIceCream2(int[] costs, int coins) {
        Arrays.sort(costs);
        int sum = 0;
        for (int i = 0; i < costs.length; i++) {
            sum += costs[i];
            if (sum > coins) {
                return i;
            }
        }
        if (sum <= coins) {
            return costs.length;
        }
        return 0;
    }
}
