package com.yglong.leetcode.dailypractice._2021._09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 502. IPO
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/ipo/
 */
public class Day_2021_09_08 {
    public static void main(String[] args) {
        System.out.println(findMaximizedCapital2(2, 0, new int[]{1, 2, 3}, new int[]{0, 9, 10}));
    }

    /**
     * 贪心算法
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int curW = w;
        k = Math.min(n, k);
        boolean[] finished = new boolean[n];
        while (k > 0) {
            int maxProfit = 0;
            int maxI = 0;

            // 这里会造成很多重复的判断，导致超时
            for (int i = 0; i < n; i++) {
                if (!finished[i] && capital[i] <= curW && maxProfit < profits[i]) {
                    maxI = i;
                    maxProfit = profits[i];
                }
            }
            finished[maxI] = true;
            curW += maxProfit;
            k--;
        }
        return curW;
    }

    /**
     * 堆 + 贪心
     */
    public static int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        // 将项目按照所需资本从小到大排序
        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));

        // 用一个堆来保存当前所有能够投资的项目的利润
        // 当前能投资的项目：就是需要的资本小于等于拥有的资本的项目
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int cur = 0; // 当前项目
        for (int i = 0; i < k; i++) {
            while (cur < n && projects[cur][0] <= w) {
                queue.offer(projects[cur][1]);
                cur++;
            }

            // 当没有满足条件的项目时，退出
            if (queue.isEmpty()) break;

            // 从满足条件的所有项目中，选择利润最大的项目，堆顶的就是最大的
            w += queue.poll();
        }
        return w;
    }
}
