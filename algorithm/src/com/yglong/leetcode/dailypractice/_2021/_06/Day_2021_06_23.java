package com.yglong.leetcode.dailypractice._2021._06;

/**
 * 剑指 Offer 15. 二进制中1的个数
 * <p>
 * <p>
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
 * <p>
 * 例如，把 9表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 * <p>
 * <p>
 * 示例 1：
 *
 * <p>
 * 输入：00000000000000000000000000001011
 * <p>
 * 输出：3
 * <p>
 * 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 输入必须是长度为 32 的 二进制串 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
 */
public class Day_2021_06_23 {
    public static void main(String[] args) {
        System.out.println(hammingWeight3(0b11111111111111111111111111111101));
    }

    /**
     * Java自带方法
     */
    public static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    public static int hammingWeight2(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    public static int hammingWeight3(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }
}
