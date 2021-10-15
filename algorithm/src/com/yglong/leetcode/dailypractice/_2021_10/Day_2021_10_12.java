package com.yglong.leetcode.dailypractice._2021_10;

/**
 * 29. 两数相除
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/divide-two-integers/
 */
public class Day_2021_10_12 {

    public static void main(String[] args) {
        System.out.println(divide(0, 10));
        System.out.println(divide(10, 3));
        System.out.println(divide(1100540749, -1090366779));
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        // 符号位
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }

        // 将被除数和除数转成负数进行计算
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;

        return sign * d(dividend, divisor);
    }

    /**
     * 递归
     */
    private static int d(int dd, int dv) {
        // dd = -1, dv = -5
        // 当被除数大于等于除数时，返回
        if (dd >= dv) return dd > dv ? 0 : 1;

        int cnt = 1;
        int res = 0;
        int tdv = dv; // 记录当前除数
        // 循环减去除数，直到被除数不大于除数
        while (dd <= tdv & tdv < 0) {
            dd -= tdv;
            res += cnt;
            tdv += tdv; // 将除数翻倍
            cnt += cnt; // 将累加值翻倍
        }
        return res + d(dd, dv);
    }
}
