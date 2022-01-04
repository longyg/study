package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 326. 3的幂
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/power-of-three/
 */
public class Day_2021_09_23 {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(-1));
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(1));
        System.out.println(isPowerOfThree(2));
        System.out.println(isPowerOfThree(3));
        System.out.println(isPowerOfThree(5));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(15));
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(81));
        System.out.println(isPowerOfThree(2147483647));
    }

    /**
     * 遍历
     */
    public static boolean isPowerOfThree(int n) {
        if (n == 1) return true;
        long i = 3;
        while (i <= n) {
            if (i == n) return true;
            i *= 3;
        }
        return false;
    }

    /**
     * 试除法
     */
    public static boolean isPowerOfThree1(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 判断是否为最大 33 的幂的约数
     */
    public static boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 二分查找
     */
    public static boolean isPowerOfThree3(int n) {
        int high = 20;
        int low = 0;
        while (low <= high) {
            int mid = (low + high) >> 1;
            double y = Math.pow(3, mid);
            if (y == n) return true;
            if (y < n) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
