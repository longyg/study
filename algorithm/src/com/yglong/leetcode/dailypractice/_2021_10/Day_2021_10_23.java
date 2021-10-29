package com.yglong.leetcode.dailypractice._2021_10;

import java.util.Arrays;

/**
 * 492. 构造矩形
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/construct-the-rectangle/
 */
public class Day_2021_10_23 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructRectangle(4)));
        System.out.println(Arrays.toString(constructRectangle(37)));
    }

    public static int[] constructRectangle(int area) {
        int w = (int) Math.sqrt(area);
        while (area % w != 0) {
            w--;
        }
        return new int[]{area / w, w};
    }
}
