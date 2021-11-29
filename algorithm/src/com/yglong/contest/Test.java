package com.yglong.contest;

import java.util.Arrays;

/**
 * 将一维数组变成m*n的二维数组
 */
public class Test {
    public static void main(String[] args) {
        int[][] arr = remake(new int[]{1, 2, 3, 4}, 2, 2);
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }

    public static int[][] remake(int[] o, int m, int n) {
        int len = o.length;
        if (m * n != len) return null;

        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = o[i * n + j];
            }
        }
        return ans;
    }
}
