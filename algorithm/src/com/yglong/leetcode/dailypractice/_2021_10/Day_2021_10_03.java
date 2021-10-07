package com.yglong.leetcode.dailypractice._2021_10;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. 分数到小数
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
 */
public class Day_2021_10_03 {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(35, 20));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        long n = numerator;
        long d = denominator;
        // 如果能整除，直接返回
        if (n % d == 0) {
            return String.valueOf(n / d);
        }

        // 不能整除
        StringBuilder sb = new StringBuilder();

        // 结果为负数
        if (numerator < 0 ^ denominator < 0) {
            sb.append("-");
        }

        n = Math.abs(n);
        d = Math.abs(d);

        // 整数部分
        sb.append(n / d).append(".");

        // 小数部分
        StringBuilder fsb = new StringBuilder();
        Map<Long, Integer> m = new HashMap<>();
        long r = n % d;
        int index = 0;
        // 使用长除法计算小数部分
        while (r != 0 && !m.containsKey(r)) {
            m.put(r, index++);
            r *= 10;
            fsb.append(r / d);
            r %= d;
        }

        // 如果有循环小数，在循环部分前后加上括号
        if (r != 0) {
            fsb.insert(m.get(r), "(");
            fsb.append(")");
        }

        sb.append(fsb.toString());

        return sb.toString();
    }
}
