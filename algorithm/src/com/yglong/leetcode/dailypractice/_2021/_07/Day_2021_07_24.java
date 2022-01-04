package com.yglong.leetcode.dailypractice._2021._07;

/**
 * 1736. 替换隐藏数字得到的最晚时间
 * <p>
 * <p>
 * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
 * <p>
 * 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
 * <p>
 * 替换time 中隐藏的数字，返回你可以得到的最晚有效时间。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：time = "2?:?0"
 * <p>
 * 输出："23:50"
 * <p>
 * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：time = "0?:3?"
 * <p>
 * 输出："09:39"
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：time = "1?:22"
 * <p>
 * 输出："19:22"
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * time 的格式为 hh:mm
 * <p>
 * 题目数据保证你可以由输入的字符串生成有效的时间
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits
 */
public class Day_2021_07_24 {
    public static void main(String[] args) {
        System.out.println(maximumTime("2?:?0"));
        System.out.println(maximumTime("0?:3?"));
        System.out.println(maximumTime("1?:22"));
        System.out.println(maximumTime("?0:15"));
        char c = '3';
        int i = c;
        System.out.println(i);
    }

    /**
     * 贪心算法
     */
    public static String maximumTime(String time) {
        char[] chars = time.toCharArray();

        if (chars[0] == '?') {
            chars[0] = ('4' <= chars[1] && chars[1] <= '9') ? '1' : '2';
        }
        if (chars[1] == '?') {
            chars[1] = chars[0] == '2' ? '3' : '9';
        }
        chars[3] = chars[3] == '?' ? '5' : chars[3];
        chars[4] = chars[4] == '?' ? '9' : chars[4];
        return new String(chars);
    }
}
