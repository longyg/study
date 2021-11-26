package com.yglong.leetcode.dailypractice._2021_11;

/**
 * 458. 可怜的小猪
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/poor-pigs/
 */
public class Day_2021_11_25 {

    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int k = minutesToTest / minutesToDie;
        return (int) Math.ceil(Math.log(buckets) / Math.log(k + 1));
    }
}
