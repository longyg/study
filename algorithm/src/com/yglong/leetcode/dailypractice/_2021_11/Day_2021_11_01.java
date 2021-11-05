package com.yglong.leetcode.dailypractice._2021_11;

import java.util.HashSet;
import java.util.Set;

/**
 * 575. 分糖果
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/distribute-candies/
 */
public class Day_2021_11_01 {
    public static void main(String[] args) {
        System.out.println(distributeCandies(new int[]{1, 1, 2, 2, 3, 3}));
        System.out.println(distributeCandies(new int[]{1, 1, 2, 3}));
        System.out.println(distributeCandies(new int[]{1, 1, 1, 2, 3, 4, 5, 6}));
    }

    /**
     * 贪心法
     * <p>
     * 要使得获得的种类最大，那么对于每种类型，获得的数量应该尽量小
     */
    public static int distributeCandies(int[] candyType) {
        Set<Integer> types = new HashSet<>();
        int count = 0;

        for (int type : candyType) {
            // 如果已经获得了一半的数量，则不能再继续获得了，退出循环
            if (count == candyType.length / 2) break;
            if (!types.contains(type)) {
                types.add(type);
                count++;
            }
        }
        return types.size();
    }
}
