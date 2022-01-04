package com.yglong.leetcode.dailypractice._2021._10;

/**
 * 476. 数字的补数
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-complement/
 */
public class Day_2021_10_18 {
    public static void main(String[] args) {
        System.out.println(findComplement(5));
        System.out.println(findComplement(1));
        System.out.println(findComplement(2));
        System.out.println(findComplement(6));
    }

    /**
     * 先求出与num的二进制位个数相同的全为1的二进制数
     * 例如：当num为5时，其二进制表示为“101”，包含3个二进制位，因此求出b = “111”
     * 然后用 b 与 num 异或即可得到结果, 也就是 111 ^ 101 = 010
     */
    public static int findComplement(int num) {
        int b = 1;
        while (b < num) {
            b = (b << 1) + 1;
        }
        return b ^ num;
    }
}
