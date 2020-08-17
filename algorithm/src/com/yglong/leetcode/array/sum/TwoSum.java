package com.yglong.leetcode.array.sum;

import com.yglong.leetcode.array.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of intergers, find two numbers such that they add up to a specific target number. The
 * function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2 Please note that your returned answers (both index1 and
 * index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 */
public class TwoSum {

    /**
     * 时间复杂度O(n*n), 只适用于有序数组
     *
     * @param a it must be a sorted array.
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] a, int target) {
        int[] res = new int[] {-1, -1};
        if (a.length <= 1) {
            return res;
        }
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int sum = a[i] + a[j];
                if (sum == target) {
                    return new int[] {i+1, j+1};
                } else if (sum > target) {
                    break;
                }
            }
        }

        return res;
    }

    /**
     * 时间复杂度O(n*n)，适用于无序数组
     * @param a non-sorted array which can including negative number
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] a, int target) {
        int[] res = new int[] {-1, -1};
        if (a.length <= 1) {
            return res;
        }
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == j) {
                    continue;
                }
                if (a[i] + a[j] == target) {
                    if (i < j) {
                        return new int[] {i+1, j+1};
                    } else {
                        return new int[] {j+1, i+1};
                    }
                }
            }
        }

        return res;
    }

    /**
     * 时间复杂度O(n), 但额外增加了一个map来存储数组
     * @param a non-sorted array which can including negative number
     * @param target
     * @return
     */
    public static int[] twoSum3(int [] a, int target) {
        int[] res = new int[] {-1, -1};
        if (a.length <= 1) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }
        for (int i = 0; i < a.length; i++) {
            int another = target - a[i];
            Integer index = map.get(another);
            if (null == index) {
                continue;
            } else if (i == index) {
                continue;
            } else if (i < index) {
                return new int[] {i + 1, index + 1};
            } else{
                return new int[] {index + 1, i + 1};
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Utils.join(twoSum1(new int[] {2, 7, 11, 15}, 22), ","));
        System.out.println(Utils.join(twoSum2(new int[] {15, 2, 11, 7}, 22), ","));
        System.out.println(Utils.join(twoSum3(new int[] {11, 2, 15, 7}, 22), ","));
    }
}
