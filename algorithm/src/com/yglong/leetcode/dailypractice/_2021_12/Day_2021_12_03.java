package com.yglong.leetcode.dailypractice._2021_12;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1005. K 次取反后最大化的数组和
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * <p>
 * <p>
 * 连接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
 */
public class Day_2021_12_03 {
    public static void main(String[] args) {
        System.out.println(largestSumAfterKNegations(new int[]{4, 2, 3}, 1));
        System.out.println(largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
        System.out.println(largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2));
    }

    /**
     * 优先队列
     */
    public static int largestSumAfterKNegations(int[] nums, int k) {
        // 优先队列默认是小顶堆，即最小值在堆顶
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
        for (int i = 0; i < k; i++) {
            queue.offer(-queue.poll());
        }
        int sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }
        return sum;
    }
}
