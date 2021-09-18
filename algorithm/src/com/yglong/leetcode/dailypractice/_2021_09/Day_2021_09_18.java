package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 292. Nim 游戏
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/nim-game/
 */
public class Day_2021_09_18 {
    public static void main(String[] args) {
        System.out.println(canWinNim(4));
        System.out.println(canWinNim(1));
        System.out.println(canWinNim(2));
        System.out.println(canWinNim(5));
        System.out.println(canWinNim(6));
        System.out.println(canWinNim(8));
    }
    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
