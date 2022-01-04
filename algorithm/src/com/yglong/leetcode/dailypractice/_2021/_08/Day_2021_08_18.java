package com.yglong.leetcode.dailypractice._2021._08;

/**
 * 552. 学生出勤记录 II
 * <p>
 * <p>
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * <p>
 * 'A'：Absent，缺勤
 * <p>
 * 'L'：Late，迟到
 * <p>
 * 'P'：Present，到场
 * <p>
 * <p>
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * <p>
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * <p>
 * <p>
 * 给你一个整数 n ，表示出勤记录的长度（次数）。
 * <p>
 * 请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 2
 * <p>
 * 输出：8
 * <p>
 * 解释：
 * <p>
 * 有 8 种长度为 2 的记录将被视为可奖励：
 * <p>
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * <p>
 * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 1
 * <p>
 * 输出：3
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：n = 10101
 * <p>
 * 输出：183236316
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 105
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/student-attendance-record-ii
 */
public class Day_2021_08_18 {
    public static void main(String[] args) {
        System.out.println(new Day_2021_08_18().checkRecord2(1));
        System.out.println(new Day_2021_08_18().checkRecord2(2));
        System.out.println(new Day_2021_08_18().checkRecord2(3));
        System.out.println(new Day_2021_08_18().checkRecord2(10101));
    }

    int MOD = 1000000000 + 7;
    int[][][] memo;

    /**
     * 回溯
     */
    public int checkRecord(int n) {
        return backtrace(0, n, 0, 0);
    }

    private int backtrace(int index, int n, int aCnt, int lCnt) {
        if (index == n) {
            return 1;
        }

        int ans = 0;

        if (aCnt < 1) {
            ans += backtrace(index + 1, n, aCnt + 1, 0); // add A
            ans %= MOD;
        }

        if (lCnt < 2) {
            ans += backtrace(index + 1, n, aCnt, lCnt + 1); // add L
            ans %= MOD;
        }

        ans += backtrace(index + 1, n, aCnt, 0); // add P
        ans %= MOD;

        return ans;
    }

    /**
     * 动态规划
     */
    public int checkRecord2(int n) {
        // dp[i][j][k] 表示前 i 天有 j 个 ‘A’ 且结尾有连续 k 个 ‘L’ 的可奖励的出勤记录的数量。
        int[][][] dp = new int[n + 1][2][3];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 以P结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 2; k++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
            // 以A结尾的数量
            for (int k = 0; k <= 2; k++) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % MOD;
            }

            // 以L结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= 2; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= 1; j++) {
            for (int k = 0; k <= 2; k++) {
                sum = (sum + dp[n][j][k]) % MOD;
            }
        }
        return sum;
    }
}
