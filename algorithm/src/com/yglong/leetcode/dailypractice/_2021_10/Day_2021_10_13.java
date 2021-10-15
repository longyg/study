package com.yglong.leetcode.dailypractice._2021_10;

import java.util.ArrayList;
import java.util.List;

/**
 * 412. Fizz Buzz
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/fizz-buzz/
 */
public class Day_2021_10_13 {
    public static void main(String[] args) {
        System.out.println(fizzBuzz(15));
    }

    public static List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                ret.add("FizzBuzz");
            } else if (i % 3 == 0) {
                ret.add("Fizz");
            } else if (i % 5 == 0) {
                ret.add("Buzz");
            } else {
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }
}
