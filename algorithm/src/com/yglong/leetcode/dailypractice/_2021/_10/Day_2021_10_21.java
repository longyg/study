package com.yglong.leetcode.dailypractice._2021._10;

import java.util.Arrays;

/**
 * 66. 加一
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/plus-one/
 */
public class Day_2021_10_21 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(plusOne(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9})));
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        int i = n - 1;
        int sum = 0;
        while (i >= 0) {
            sum = digits[i] + 1;
            // 如果加1等于10，需要进位，需继续对高一位加1
            if (sum == 10) {
                digits[i] = 0;
            } else {
                // 如果加1不等于10，则不需要进位，因此退出循环
                digits[i] = sum;
                break;
            }
            i--;
        }
        // 如果最高位加1等于10，则需要进位，因此需要扩充数组
        if (sum == 10) {
            int[] arr = new int[n + 1];
            arr[0] = 1;
            // 此处无需拷贝原数组，因为剩余元素都是0
            // System.arraycopy(digits, 0, arr, 1, n);
            return arr;
        }
        return digits;
    }
}
