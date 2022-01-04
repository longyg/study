package com.yglong.leetcode.dailypractice._2021._11;

/**
 * 367. 有效的完全平方数
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-perfect-square/
 */
public class Day_2021_11_04 {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16));
        System.out.println(isPerfectSquare(14));
        System.out.println(isPerfectSquare(1));
        System.out.println(isPerfectSquare(4));
        System.out.println(isPerfectSquare(2));
        System.out.println(isPerfectSquare(400));
        System.out.println(isPerfectSquare(808201));
    }

    public static boolean isPerfectSquare(int num) {
        return binarySearch(num >= 2 ? num / 2 : num, num);
    }

    /**
     * 二分查找
     */
    private static boolean binarySearch(int high, int num) {
        int low = 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            long t = (long) mid * mid;
            if (t == num) {
                return true;
            } else if (t > num) {
                high = mid - 1;
            } else if (t < num) {
                low = mid + 1;
            }
        }
        return false;
    }
}
