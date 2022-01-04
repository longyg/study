package com.yglong.leetcode.dailypractice._2021._09;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 面试题 17.14. 最小K个数
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci/
 */
public class Day_2021_09_03 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(smallestK2(new int[]{1, 3, 5, 7, 2, 4, 6, 8}, 4)));
    }

    /**
     * 排序
     */
    public static int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ret = new int[k];
        System.arraycopy(arr, 0, ret, 0, k);
        return ret;
    }

    /**
     * 堆
     */
    public static int[] smallestK2(int[] arr, int k) {
        int[] ret = new int[k];
        if (k == 0) {
            return ret;
        }

        // 大顶堆
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // 把前k个元素放入堆
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        // 从第k个元素开始，比较当前元素与堆顶元素
        // 如果堆顶元素大，则弹出，并把当前元素添加进堆
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll();
        }
        return ret;
    }
}
