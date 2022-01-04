package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 1894. 找到需要补充粉笔的学生编号
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/
 */
public class Day_2021_09_10 {
    public static void main(String[] args) {
        System.out.println(chalkReplacer(new int[]{5, 1, 5}, 22)); // 0
        System.out.println(chalkReplacer(new int[]{3, 4, 1, 2}, 25)); // 1
        System.out.println(chalkReplacer(new int[]{1}, 2)); // 0
        System.out.println(chalkReplacer(new int[]{2}, 1)); // 0
        System.out.println(chalkReplacer(new int[]{1, 2}, 3)); // 0
    }

    /**
     * 循环累计
     */
    public static int chalkReplacer1(int[] chalk, int k) {
        int n = chalk.length;
        int i = 0;
        while (chalk[i] <= k) {
            k -= chalk[i];
            i = i == n - 1 ? 0 : i + 1;
        }
        return i;
    }

    /**
     * 求余法
     */
    public static int chalkReplacer(int[] chalk, int k) {
        long sum = 0;
        for (int j : chalk) {
            sum += j;
        }
        long y = k % sum;
        int i = 0;
        while (chalk[i] <= y) {
            y -= chalk[i];
            i++;
        }
        return i;
    }
}
