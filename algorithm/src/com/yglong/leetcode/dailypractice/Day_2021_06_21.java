package com.yglong.leetcode.dailypractice;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. 二进制手表
 * <p>
 * <p>
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * 例如，下面的二进制手表读取 "3:25" 。
 * <p>
 * <p>
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 * <p>
 * <p>
 * 小时不会以零开头：
 * <p>
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * <p>
 * <p>
 * 分钟必须由两位数组成，可能会以零开头：
 * <p>
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：turnedOn = 1
 * <p>
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：turnedOn = 9
 * <p>
 * 输出：[]
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-watch
 */
public class Day_2021_06_21 {
    public static void main(String[] args) {
        System.out.println(readBinaryWatch2(5));
    }

    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> l = new ArrayList<>();
        for (int i = 0; i < 12; i++) { // 时钟
            for (int j = 0; j < 60; j++) { // 分钟
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    l.add(i + ":" + (j < 10 ? "0" : "") + j);
                }
            }
        }
        return l;
    }

    public static List<String> readBinaryWatch2(int turnedOn) {
        List<String> l = new ArrayList<>();
        for (int i = 0; i < 1024; i++) { // 10个灯总共可以表示1024个数
            int h = i >> 6;
            int m = i & 63;
            if (h < 12 && m < 60 && Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                l.add(h + ":" + (m < 10 ? "0" : "") + m);
            }
        }
        return l;
    }
}
