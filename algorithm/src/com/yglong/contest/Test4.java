package com.yglong.contest;

/**
 * 找到从矩阵的左上角到右下角的所有路径中的最大总和
 */
public class Test4 {
    public static void main(String[] args) {
        System.out.println(findLargest(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    public static int findLargest(int[][] array) {
        int m = array.length;
        int n = array[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = array[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (j == 0) continue;
                    dp[i][j] = dp[i][j - 1] + array[i][j];
                } else {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][j] + array[i][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + array[i][j];
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
