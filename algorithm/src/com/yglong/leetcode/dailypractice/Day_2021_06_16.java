package com.yglong.leetcode.dailypractice;

/**
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石piles[i]。
 * <p>
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * <p>
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * <p>
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回true，当李赢得比赛时返回false。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入：[5,3,4,5]
 * <p>
 * 输出：true
 * <p>
 * <p>
 * 解释：
 * <p>
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * <p>
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * <p>
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * <p>
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * <p>
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/stone-game
 */
public class Day_2021_06_16 {
    public static void main(String[] args) {
        //System.out.println(stoneGame(new int[] {7,7,12,16,41,48,41,48,11,9,34,2,44,30,27,12,11,39,31,8,23,11,47,25,15,23,4,17,11,50,16,50,38,34,48,27,16,24,22,48,50,10,26,27,9,43,13,42,46,24}));
        System.out.println(stoneGame3(new int[] {7,7,12,16,41,48,41,48,11,9,34,2,44,30,27,12,11,39,31,8,23,11,47,25,15,23,4,17,11,50,16,50,38,34,48,27,16,24,22,48,50,10,26,27,9,43,13,42,46,24}) > 0);
    }

    /**
     * 递归法，时间复杂度O(2^n)
     */
    public static boolean stoneGame(int[] piles) {
        int ret = stoneGame(piles, 0, piles.length - 1);
        return ret > 0;
    }

    public static int stoneGame(int[] piles, int start, int end) {
        if (start == end) { // 只剩最后一堆
            return piles[start];
        }
        // 如果选择start
        int ret0 = stoneGame(piles, start + 1, end);
        int ret1 = stoneGame(piles, start, end - 1);
        int a = piles[start];
        int b = piles[end];
        return Math.max((a - ret0), (b - ret1));
    }

    /**
     * 动态规划，二维DP表
     */
    public static int stoneGame2(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (i == j) {
                    dp[i][j] = piles[i];
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max((piles[i] - dp[i + 1][j]), (piles[j] - dp[i][j - 1]));
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 动态规划，一维DP表
     */
    public static int stoneGame3(int[] piles) {
        int n = piles.length;
        int[] dp = new int[n];
        System.arraycopy(piles, 0, dp, 0, n);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }

        return dp[piles.length - 1];
    }
}
