package com.yglong.leetcode.dailypractice._2021._12;

/**
 * 1154. 一年中的第几天
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 * <p>
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/day-of-the-year
 */
public class Day_2021_12_21 {
    public static void main(String[] args) {
        System.out.println(dayOfYear("2019-01-09"));
        System.out.println(dayOfYear("2019-02-10"));
        System.out.println(dayOfYear("2003-03-01"));
        System.out.println(dayOfYear("2004-03-01"));
    }

    /**
     * 迭代法
     */
    public static int dayOfYear1(String date) {
        String[] ss = date.split("-");
        int y = Integer.parseInt(ss[0]);
        int m = Integer.parseInt(ss[1]);
        int d = Integer.parseInt(ss[2]);

        if (m == 1) {
            return d;
        } else if (m == 2) {
            return 31 + d;
        }

        boolean isLeap = false; // 是否为闰年
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
            isLeap = true;
        }
        int total = 31 + (isLeap ? 29 : 28); // 闰年29天，非闰年28天
        for (int i = 3; i <= m; i++) {
            if (i == m) {
                return total + d;
            }
            // 4，6，9，11月有30天，其他月有31天
            total += (i == 4 || i == 6 || i == 9 || i == 11) ? 30 : 31;
        }
        return total;
    }

    /**
     * 数组
     */
    public static int dayOfYear(String date) {
        int[] mm = new int[]{0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] ss = date.split("-");
        int y = Integer.parseInt(ss[0]);
        int m = Integer.parseInt(ss[1]);
        int d = Integer.parseInt(ss[2]);
        boolean isLeap = false; // 是否为闰年
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
            isLeap = true;
        }
        mm[2] = isLeap ? 29 : 28;
        int total = 0;
        for (int i = 1; i < m; i++) {
            total += mm[i];
        }
        return total + d;
    }
}
