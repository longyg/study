package com.yglong.leetcode.dailypractice._2021._12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 630. 课程表 III
 * <p>
 * <p>
 * 难度：困难
 * <p>
 * <p>
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
 * <p>
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 * <p>
 * 返回你最多可以修读的课程数目。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/course-schedule-iii
 */
public class Day_2021_12_14 {
    public static void main(String[] args) {
        System.out.println(scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}));
        System.out.println(scheduleCourse(new int[][]{{3, 2}, {4, 3}}));
    }

    /**
     * 贪心
     */
    public static int scheduleCourse(int[][] courses) {
        // 根据last Day 排序，last day在前的排在前面
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        int sumDuration = 0;
        Queue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            if (sumDuration + ti <= di) {
                sumDuration += ti;
                q.offer(ti);
            } else if (!q.isEmpty() && q.peek() > ti) {
                sumDuration -= q.poll() - ti;
                q.offer(ti);
            }
        }
        return q.size();
    }
}
