package com.yglong.leetcode.dailypractice._2021_06;

/**
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * <p>
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * <p>
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * <p>
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * <p>
 * 输出：2
 * <p>
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * <p>
 * 输出：7
 * <p>
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/profitable-schemes
 */
public class Day_2021_06_09 {
    public static void main(String[] args) {
        int n = 10, minProfit = 5;
        int[] group = new int[]{2,3,5};
        int[] profit = new int[]{6,7,8};
        System.out.println(profitableSchemes2(n, minProfit, group, profit));
    }

    /**
     * 动态规划
     */
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int MOD = (int) 1e9 + 7;
        int len = group.length;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1]; // 定义DP table
        dp[0][0][0] = 1; // base case
        for (int i = 1; i <= len; i++) { // 当前正在做的工作
            int members = group[i - 1]; // 完成工作i需要多少员工
            int earn = profit[i - 1]; // 完成当前工作可以盈利多少
            for (int j = 0; j <= n; j++) { // j 表示当前可用员工的数量
                for (int k = 0; k <= minProfit; k++) {
                    if (j < members) { // 如果当前可用员工数量 < 需要的员工数量，则无法完成当前工作
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int ret = 0;
        for (int j = 0; j <= n; j++) {
            ret = (ret + dp[len][j][minProfit]) % MOD;
        }
        return ret;
    }

    /**
     * 动态规划 优化版
     */
    public static int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1; // base case
        }
        int MOD = (int) 1e9 + 7;
        int len = group.length;
        for (int i = 1; i <= len; i++) { // 当前正在做的工作
            int members = group[i - 1]; // 完成工作i需要多少员工
            int earn = profit[i - 1]; // 完成当前工作可以盈利多少
            for (int j = n; j >= members; j--) { // j 表示当前可用员工的数量
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j-members][Math.max(0, k - earn)]) % MOD;
                }
            }
        }
        return dp[n][minProfit];
    }
}
