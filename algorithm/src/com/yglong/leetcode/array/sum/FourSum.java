package com.yglong.leetcode.array.sum;

import com.yglong.leetcode.array.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c and d in S such that a+b+c+d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 * 1. Elements in quadruplets (a, b, c, d) must be in non-descending order. (ie, a<=b<=c<=d)
 * 2. The solution must not contain duplicates quadruplets.
 */
public class FourSum {
    public static List<int[]> fourSum(int[] a, int target) {
        List<int[]> res = new ArrayList<>();
        if (a.length <= 3) {
            return res;
        }

        Arrays.sort(a);
        System.out.println(Utils.join(a, ","));

        for (int i = 0; i < a.length - 3; i++) {
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < a.length - 2; j++) {
                if (j > 0 && a[j] == a[j - 1]) {
                    continue;
                }
                int k = j + 1;
                int z = a.length - 1;
                while (k < z) {
                    int sum = a[i] + a[j] + a[k] + a[z];
                    if (sum == target) {
                        res.add(new int[]{a[i], a[j], a[k], a[z]});
                        do {
                            k++;
                        } while (k < z && a[k - 1] == a[k]);
                        do {
                            z--;
                        } while (k < z && a[z] == a[z + 1]);
                    } else if (sum < target) {
                        k++;
                    } else {
                        z--;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] a = new int[] {10, -1, -8, -40, -9, 40, 0, 3, -2, 7, 1, 6, 6, -12, 6};
        List<int[]> res = fourSum(a, 0);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(Utils.join(res.get(i), ","));
        }
    }
}
