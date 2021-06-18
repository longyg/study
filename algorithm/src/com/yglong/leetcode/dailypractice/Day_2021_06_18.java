package com.yglong.leetcode.dailypractice;

/**
 * 483. 最小好进制
 * <p>
 * <p>
 * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
 * <p>
 * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入："4681"
 * <p>
 * 输出："8"
 * <p>
 * 解释：4681 的 8 进制是 11111。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/smallest-good-base
 */
public class Day_2021_06_18 {
    public static void main(String[] args) {
        System.out.println(smallestGoodBase("1000000000000000000"));
    }

    /**
     * 暴力法
     */
    public static String smallestGoodBase(String n) {
        long nn = Long.parseLong(n);
        long maxK = nn - 1;
        for (long k = 2; k <= maxK; k++) {
            long maxR = (long) (Math.log(nn) / Math.log(k)); // 最高位
            long sum = 0; // sum = 1*k^j + 1*k^j-1 + ... 1*k^0
            for (long j = maxR; j >= 0; j--) {
                sum += Math.pow(k, j);
            }
            if (sum == nn) {
                return Long.toString(k);
            }
        }
        return Long.toString(maxK);
    }
}
