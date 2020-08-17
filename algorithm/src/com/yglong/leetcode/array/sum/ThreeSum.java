package com.yglong.leetcode.array.sum;

import com.yglong.leetcode.array.utils.Utils;

import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all
 * unique triplets in the array which gives the sum of zero.
 * Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c) The solution set
 * must not contain duplicate triplets.
 */
public class ThreeSum {

    /**
     * 常规算法，时间复杂度高 O（n * n * n)
     * @param a
     * @return
     */
    public static List<int[]> threeSum(int[] a) {
        List<int[]> list = new ArrayList<>();
        if (a.length <= 2) {
            return list;
        }

        Arrays.sort(a);
        System.out.println(Utils.join(a, ","));

        Map<String, String> resMap = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        String key = a[i] + "_" + a[j] + "_" + a[k];
                        resMap.putIfAbsent(key, "");
                    }
                }
            }
        }


        resMap.keySet().forEach(key -> {
            String[] values = key.split("_");
            list.add(new int[] {Integer.valueOf(values[0]), Integer.valueOf(values[1]), Integer.valueOf(values[2])});
        });

        return list;
    }

    /**
     * 优化算法，时间复杂度降低 O(n*n)
     * @param a
     * @return
     */
    public static List<int[]> threeSum2(int[] a) {
        List<int[]> list = new ArrayList<>();
        if (a.length <= 2) {
            return list;
        }

        Arrays.sort(a);
        System.out.println(Utils.join(a, ","));

        for (int i = 0; i < a.length - 2; i++) {
            int j = i + 1;
            int k = a.length - 1;
            while (j < k) {
                int sum = a[i] + a[j] + a[k];
                if (sum == 0) {
                    list.add(new int[] {a[i], a[j], a[k]});
                    j++;
                    k--;
                    while (j < k && a[j - 1] == a[j]) {
                        j++;
                    }
                    while (j < k && a[k] == a[k + 1]) {
                        k--;
                    }
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
            while (i < a.length - 1 && a[i] == a[i + 1]) {
                i++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] a = new int[] {10, -1, -8, -40, -9, 40, 0, 3, -2, 7, 1, 6, 6, -12, 6};
        List<int[]> res = threeSum(a);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(Utils.join(res.get(i), ","));
        }

        List<int[]> res2 = threeSum2(a);
        for (int i = 0; i < res2.size(); i++) {
            System.out.println(Utils.join(res2.get(i), ","));
        }

        int[] b = new int[] {0,0,0,0,0,0,0,0,0};
        List<int[]> resb = threeSum(b);
        for (int i = 0; i < resb.size(); i++) {
            System.out.println(Utils.join(resb.get(i), ","));
        }

        List<int[]> resb2 = threeSum2(b);
        for (int i = 0; i < resb2.size(); i++) {
            System.out.println(Utils.join(resb2.get(i), ","));
        }
    }
}
