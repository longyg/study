package com.yglong.leetcode.array.pascaltriangle;

import com.yglong.leetcode.array.utils.Utils;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5, Return
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {

    public static int[][] pascalTriangle(int numRows) {
        int[][] res = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            res[i] = new int[i + 1];
            // 每一行的第一个数为1
            res[i][0] = 1;
            // 每一行的第二个数为1
            res[i][res[i].length - 1] = 1;
            for (int j = 1; j < res[i].length - 1; j++) {
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] res2 = pascalTriangle(6);
        for (int i = 0; i < res2.length; i++) {
            System.out.println(Utils.join(res2[i], ","));
        }
    }
}
