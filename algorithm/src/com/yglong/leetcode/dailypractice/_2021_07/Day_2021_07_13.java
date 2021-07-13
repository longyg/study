package com.yglong.leetcode.dailypractice._2021_07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 218. 天际线问题
 * <p>
 * <p>
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。
 * <p>
 * 给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
 * <p>
 * <p>
 * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 * <p>
 * <p>
 * lefti 是第 i 座建筑物左边缘的 x 坐标。
 * <p>
 * righti 是第 i 座建筑物右边缘的 x 坐标。
 * <p>
 * heighti 是第 i 座建筑物的高度。
 * <p>
 * <p>
 * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。
 * <p>
 * 关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。
 * <p>
 * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * <p>
 * <p>
 * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
 * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 * <p>
 * <p>
 * 示例1:
 * <p>
 * <p>
 * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * <p>
 * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * <p>
 * 解释：
 * <p>
 * 图 A 显示输入的所有建筑物的位置和高度，
 * <p>
 * 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/the-skyline-problem。
 */
public class Day_2021_07_13 {

    public static void main(String[] args) {
        List<List<Integer>> result = getSkyline2(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
        System.out.println(Arrays.toString(result.toArray()));
    }

    /**
     * 扫描线
     * 时间复杂度O(n^2)
     */
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();

        int[] boundaries = new int[buildings.length * 2];
        int n = 0;
        for (int[] building : buildings) {
            boundaries[n++] = building[0];
            boundaries[n++] = building[1];
        }

        Arrays.sort(boundaries);

        int prevHeight = 0; // 保存上一个最大高度

        for (int i = 0; i < boundaries.length - 1; i++) {
            int left = boundaries[i];
            int right = boundaries[i + 1];

            int maxHeight = 0;

            for (int[] building : buildings) {
                int l = building[0];
                int r = building[1];
                int height = building[2];

                if (l >= right) {
                    break;
                }

                if (l <= left && r >= right) {
                    if (maxHeight < height) {
                        maxHeight = height;
                    }
                }
            }
            if (maxHeight != prevHeight) { // 如果当前最大高度不等于之前的最大高度，添加关键点
                result.add(Arrays.asList(left, maxHeight));
            }
            prevHeight = maxHeight;
        }

        result.add(Arrays.asList(boundaries[boundaries.length - 1], 0));

        return result;
    }


    /**
     * 扫描线+优先队列
     * 时间复杂度O(nlogn)
     */
    public static List<List<Integer>> getSkyline2(int[][] buildings) {
        List<int[]> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            int l = building[0];
            int r = building[1];
            int h = building[2];
            boundaries.add(new int[]{l, -h});
            boundaries.add(new int[]{r, h});
        }
        boundaries.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        List<List<Integer>> ret = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int prev = 0;
        pq.add(prev);
        for (int[] boundary : boundaries) {
            int point = boundary[0];
            int height = boundary[1];
            if (height < 0) {
                // 如果是左边界，将高度加入队列
                pq.offer(-height);
            } else {
                // 如果是右边界，将高度移除队列
                pq.remove(height);
            }
            Integer cur = pq.peek(); // 队列的第一个元素为最高高度
            if (cur != prev) {
                ret.add(Arrays.asList(point, cur));
                prev = cur;
            }
        }
        return ret;
    }
}
