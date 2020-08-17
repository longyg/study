package com.yglong.leetcode.array.sum;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given
 * number: target.
 * Return the sum of the three integers. You man assume that each input would have
 * exactly one solution.
 */
public class ThreeSumClosest {

    /**
     * 常规遍历方法，复杂度高O(n*n*n)
     * @param a
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] a, int target) {
        int gap = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    int tmp = a[i] + a[j] + a[k];
                    int curGap = Math.abs(target - tmp);
                    if ( curGap <= gap) {
                        sum = tmp;
                        gap = curGap;
                    }
                }
            }
        }

        return sum;
    }

    /**
     * 优化算法，时间复杂度O(n*n)
     * @param a
     * @param target
     * @return
     */
    public static int threeSumClosest2(int[] a, int target) {
        Arrays.sort(a);

        int gap = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < a.length - 2; i++) {
            int j = i + 1;
            int k = a.length - 1;
            while (j < k) {
                int tmp = a[i] + a[j] + a[k];
                int curGap = Math.abs(target - tmp);
                if (curGap < gap) {
                    sum = tmp;
                    gap = curGap;
                }
                if (tmp < target) {
                    j++;
                } else if (tmp > target) {
                    k--;
                } else {
                    return sum;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[] {-10, -5, 10, -2, 2, 3}, 4));
        System.out.println(threeSumClosest2(new int[] {-10, -5, 10, -2, 2, 3}, 4));
    }

}