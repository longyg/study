package com.yglong.leetcode.dailypractice._2021_08;

import java.util.Random;

/**
 * 470. 用 Rand7() 实现 Rand10()
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7/
 */
public class Day_2021_09_05 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(rand10());
        }
    }

    public static int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            if (num <= 40) return num % 10 + 1;
            num = (num - 40 - 1) * 7 + rand7();
            if (num <= 60) return num % 10 + 1;
            num = (num - 60 - 1) * 7 + rand7();
            if (num <= 20) return num % 10 + 1;
        }
    }

    private static int rand7() {
        Random random = new Random();
        return random.nextInt(7) + 1;
    }
}
