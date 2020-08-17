package com.yglong.leetcode.array;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOne {

    /**
     * add v to digits
     * @param digits a number represent as an array of digits
     * @param v a number of 1 - 9
     * @return
     */
    public static int[] plus(int[] digits, int v) {
        int[] res = digits;
        int sum = 0;
        int number = v;
        // 从数组的高位开始加，也就是整数的个位
        for (int i = digits.length - 1; i >= 0; i--) {
            sum = digits[i] + number;
            digits[i] = sum % 10;
            number = sum / 10;
        }
        if (number > 0) {
            res = new int[digits.length + 1];
            res[0] = number;
            for (int i = 1; i < res.length; i++) {
                res[i] = digits[i - 1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] res = plus(new int[] {9, 2, 8, 5}, 1);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
