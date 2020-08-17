package com.yglong.leetcode.array.pascaltriangle;

import com.yglong.leetcode.array.utils.Utils;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3, Return [1,3,3,1].
 *
 *  1
 *  1, 1
 *  1, 2, 1
 *  1, 3, 3, 1  <--
 *  1, 4, 6, 4, 1
 *  1, 5, 10, 10, 5, 1
 *  1, 6, 15, 20, 15, 6, 1
 *
 */
public class PascalTriangle2 {

    /**
     * 从前往后计算，复杂度高
     * @param k
     * @return
     */
    public static int[] pascalTriangle(int k) {
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[0] = 1;
            res[i] = 1;
            for (int j = 1; j < i; j++) {
                int last = res[j - 1];
                if (j >= 2) {
                    int flag = 0;
                    for (int n = j - 2; n >= 0; n--) {
                        if (flag == 0) {
                            last = last - res[n];
                            flag = 1;
                        } else {
                            last = last + res[n];
                            flag = 0;
                        }
                    }
                }
                res[j] = res[j] + last;
            }
        }

        return res;
    }

    /**
     * 从后往前计算，最简单
     * @param k
     * @return
     */
    public static int[] pascalTriangle2(int k) {
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[0] = 1;
            res[i] = 1;
            if (i > 1) {
                for (int j = i - 1; j > 0; j--) {
                    res[j] = res[j] + res[j - 1];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Utils.join(pascalTriangle(5), ","));
        System.out.println(Utils.join(pascalTriangle2(5), ","));
    }
}
