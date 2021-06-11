package com.yglong.leetcode.dailypractice;

/**
 * 279. 完全平方数
 * <p>
 * <p>
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * <p>
 * 输出：3
 * <p>
 * 解释：12 = 4 + 4 + 4
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 13
 * <p>
 * 输出：2
 * <p>
 * 解释：13 = 4 + 9
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 */
public class Day_2021_06_11 {
    public static void main(String[] args) {
        System.out.println(numSquares1(12) == 3);
        System.out.println(numSquares(13) == 2);
        System.out.println(numSquares(26) == 2);
    }

    /**
     * 动态规划，二维DP表
     */
    public static int numSquares(int n) {
        int len = (int) Math.sqrt(n);
        // 初始化nums数组，即可以用于计算的完全平方数，不能大于n
        // 假如n为13，nums只能取1, 4, 9
        int[] nums = new int[len]; // nums 数组存储了可取的完全平方数，如1, 4, 9，最大的不能超过n
        for (int i = 0; i < len; i++) {
            nums[i] = (i + 1) * (i + 1);
        }

        int[][] dp = new int[len][n + 1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // 当只有完全平方数1时，至少需要n个1
        }
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 0; j <= n; j++) {
                if (j < num) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 当j大于当前num时，可以选择使用num，或不使用num，二者取最小
                    dp[i][j] = Math.min(1 + dp[i][j - num], dp[i - 1][j]);
                }
            }
        }
        return dp[len - 1][n];
    }

    /**
     * 动态规划，一维DP表
     */
    public static int numSquares1(int n) {
        int len = (int) Math.sqrt(n);
        // 初始化nums数组，即可以用于计算的完全平方数，不能大于n
        // 假如n为13，nums只能取1, 4, 9
        int[] nums = new int[len]; // nums 数组存储了可取的完全平方数，如1, 4, 9，最大的不能超过n
        for (int i = 0; i < len; i++) {
            nums[i] = (i + 1) * (i + 1);
        }

        int[] dp = new int[n + 1];
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int num : nums) {
            for (int j = num; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - num] + 1);
            }
        }

        return dp[n];
    }
}
