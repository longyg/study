package com.yglong.leetcode.dailypractice;

import jdk.internal.org.objectweb.asm.Handle;

import java.util.*;

/**
 * 645. 错误的集合
 * <p>
 * <p>
 * 集合 s 包含从 1 到n的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
 * 导致集合 丢失了一个数字 并且 有一个数字重复 。
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * <p>
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/set-mismatch
 */
public class Day_2021_07_04 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findErrorNums(new int[]{3,2,3,4,6,5})));
    }

    /**
     * 排序
     */
    public static int[] findErrorNums(int[] nums) {
        int[] ret = new int[2];
        int n = nums.length;
        Arrays.sort(nums);
        int prev = 0;
        for (int cur : nums) {
            if (prev == cur) {
                ret[0] = cur;
            } else if (cur - prev > 1) {
                ret[1] = prev + 1;
            }
            prev = cur;
        }
        if (nums[n - 1] != n) {
            ret[1] = n;
        }
        return ret;
    }


    /**
     * 哈希表
     */
    public static int[] findErrorNums2(int[] nums) {
        int[] ret = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int i = 1; i <= nums.length; i++) {
            Integer n = map.getOrDefault(i, 0);
            if (n == 2) {
                ret[0] = i;
            } else if (n == 0) {
                ret[1] = i;
            }
        }
        return ret;
    }

    /**
     * 位运算
     */
    public int[] findErrorNums3(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        int lowbit = xor & (-xor);
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & lowbit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & lowbit) == 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }
        for (int num : nums) {
            if (num == num1) {
                return new int[]{num1, num2};
            }
        }
        return new int[]{num2, num1};
    }
}
