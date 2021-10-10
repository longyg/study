package com.yglong.leetcode.dailypractice._2021_10;

/**
 * 441. 排列硬币
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/arranging-coins/
 */
public class Day_2021_10_10 {
    public static void main(String[] args) {
        System.out.println(arrangeCoins(5));
        System.out.println(arrangeCoins(1));
        System.out.println(arrangeCoins(8));
        System.out.println(arrangeCoins(6));
    }

    /**
     * 累加法
     */
    public static int arrangeCoins1(int n) {
        int line = 1;
        int sum = 1;
        while (true) {
            sum += line + 1;
            if (sum > n) {
                break;
            }
            line++;
        }
        return line;
    }


    /**
     * 二分查找法
     */
    public static int arrangeCoins(int n) {
        long l = 1, r = n;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if ((mid + 1) * mid / 2 <= n) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return (int) r;
    }
}
