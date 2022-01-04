package com.yglong.leetcode.dailypractice._2021._11;

/**
 * 319. 灯泡开关
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/bulb-switcher/
 */
public class Day_2021_11_15 {
    public static void main(String[] args) {
        System.out.println(bulbSwitch(0));
        System.out.println(bulbSwitch(1));
        System.out.println(bulbSwitch(2));
        System.out.println(bulbSwitch(3));
        System.out.println(bulbSwitch(4));
        System.out.println(bulbSwitch(5));
        System.out.println(bulbSwitch(12));
    }

    /**
     * 迭代法
     */
    public static int bulbSwitch1(int n) {
        boolean[] stat = new boolean[n];
        for (int i = 2; i <= n; i++) { // 从第2轮开始
            int j = 1, g = i * j - 1;
            while (g < n) {
                stat[g] = !stat[g];
                g = i * ++j - 1;
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!stat[i]) cnt++;
        }
        return cnt;
    }

    public static int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
