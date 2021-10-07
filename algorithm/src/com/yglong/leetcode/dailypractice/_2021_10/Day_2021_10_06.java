package com.yglong.leetcode.dailypractice._2021_10;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 414. 第三大的数
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/third-maximum-number/
 */
public class Day_2021_10_06 {
    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{2, 2, 3, 2}));
        System.out.println(thirdMax(new int[]{2, 1}));
        System.out.println(thirdMax(new int[]{1}));
        System.out.println(thirdMax(new int[]{1, 2, 3, 4, 5}));
    }

    /**
     * 大顶堆
     */
    public static int thirdMax1(int[] nums) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            queue.add(num);
        }
        int a = queue.poll(); // 最大的
        int b = a;
        int i = 1;
        while (!queue.isEmpty()) {
            if (i == 3) break;
            int t = queue.poll();
            if (t == b) continue;
            b = t;
            i++;
        }
        // 如果有第三大的数，则返回它
        if (i == 3) return b;
        // 否则返回最大的
        return a;
    }

    public static int thirdMax(int[] nums) {
        Integer a = null, b = null, c = null;
        for (int num : nums) {
            if (a == null || num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && (b == null || num > b)) {
                c = b;
                b = num;
            } else if (b != null && b > num && (c == null || num > c)) {
                c = num;
            }
        }
        return c == null ? a : c;
    }
}
