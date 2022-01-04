package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 371. 两整数之和
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers/
 */
public class Day_2021_09_26 {

    public static void main(String[] args) {
        System.out.println(2 & 3);
        System.out.println(getSum(2, 3));
    }

    public static int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
