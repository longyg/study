package com.yglong.leetcode.dailypractice._2021._12;

import java.util.Arrays;

/**
 * 846. 一手顺子
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * <p>
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/hand-of-straights
 */
public class Day_2021_12_30 {
    public static void main(String[] args) {
        System.out.println(isNStraightHand(new int[]{1, 2, 3}, 1));
        System.out.println(isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
        System.out.println(isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println(isNStraightHand(new int[]{1, 2, 3, 4, 5, 7}, 3));
    }

    /**
     * 排序 + 迭代
     */
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) return false;
        if (groupSize < 2) return true;
        Arrays.sort(hand);

        boolean[] used = new boolean[n];
        int cnt = 1;
        int index = -1;
        int i = 0;
        while (i < n - 1) {
            used[i] = true;
            int j = i + 1;
            int lastJ = i;
            while (j < n) {
                if (!used[j]) {
                    if (hand[j] - hand[lastJ] == 1) {
                        cnt++;
                        used[j] = true; // 设置当前元素已经被使用
                        lastJ = j;
                        if (cnt == groupSize) { // 找到了一组，退出
                            break;
                        }
                    } else {
                        // 如果当前元素与上一个元素不能组成一组，
                        // 且是最前面的没有被使用的元素，则保存其位置，下一轮从这个位置开始
                        index = index == -1 ? j : index;
                    }
                }
                j++;
            }
            // 如果遍历到最后都找不到足够多的牌组成一组，则返回false
            if (cnt != groupSize) return false;

            // 否则，已经找到了一组，开始查找下一组
            cnt = 1; // 将个数归1
            i = index == -1 ? j + 1 : index; // 下一轮开始查找的元素为最前面的没有被使用的元素
            index = -1;
        }
        return true;
    }
}
