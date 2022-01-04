package com.yglong.leetcode.dailypractice._2021._11;

/**
 * 400. 第 N 位数字
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * <p>
 * 输出：3
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 11
 * <p>
 * 输出：0
 * <p>
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *  
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2^31 - 1
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/nth-digit/
 */
public class Day_2021_11_30 {
    public static void main(String[] args) {
        System.out.println(findNthDigit(11));
        System.out.println(findNthDigit(1000000000));
    }

    /**
     * 分段计算
     */
    public static int findNthDigit(int n) {
        long d = 1; // 记录当前区间的十进制位数
        long low; // 记录区间的最小数字个数
        long high = 0; // 记录区间的最大数字个数
        while (true) {
            low = high + 1;
            high += (Math.pow(10, d) - Math.pow(10, d - 1)) * d;
            if (n >= low && n <= high) { // 如果n在low到high区间内
                // 计算n对应的整数
                long number = (long) Math.pow(10, d - 1) + (n - low) / d;
                // 计算n对应的整数的第几位字符
                char c = Long.toString(number).charAt((int) ((n - low) % d));
                return Integer.parseInt(Character.toString(c));
            }
            // 如果区间最大值超过了上限，退出
            if (high > Integer.MAX_VALUE) break;
            d++;
        }
        return 0;
    }
}
