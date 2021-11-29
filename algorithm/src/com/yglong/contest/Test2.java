package com.yglong.contest;

/**
 * 判断01字符串中，连续1的长度是否比连续0的长度长
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(checkZeroOnes("1"));
        System.out.println(checkZeroOnes("1101"));
        System.out.println(checkZeroOnes("111000"));
        System.out.println(checkZeroOnes("110100010"));
    }

    public static boolean checkZeroOnes(String s) {
        int maxOne = 0;
        int maxZero = 0;
        int one = 0, zero = 0;
        char lastC = 'a';
        for (char c : s.toCharArray()) {
            if (c == '1') {
                if (lastC == '1') {
                    one++;
                } else {
                    if (one > maxOne) {
                        maxOne = one;
                    }
                    one = 1;
                }
            }
            lastC = c;
        }
        if (one > maxOne) {
            maxOne = one;
        }
        lastC = 'a';
        for (char c : s.toCharArray()) {
            if (c == '0') {
                if (lastC == '0') {
                    zero++;
                } else {
                    if (zero > maxZero) {
                        maxZero = zero;
                    }
                    zero = 1;
                }
            }
            lastC = c;
        }
        if (zero > maxZero) {
            maxZero = zero;
        }
        return maxOne > maxZero;
    }
}
