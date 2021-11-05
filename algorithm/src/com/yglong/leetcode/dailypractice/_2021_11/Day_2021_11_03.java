package com.yglong.leetcode.dailypractice._2021_11;

import java.util.PriorityQueue;

/**
 * 407. 接雨水 II
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water-ii/
 */
public class Day_2021_11_03 {

    public static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        // 用于存储每个点的路径高度
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] vis = new boolean[m][n];
        // 初始化上下边界上的每个点的路径高度
        for (int i = 0; i < n; i++) {
            q.add(new int[]{0, i, heightMap[0][i]});
            q.add(new int[]{m - 1, i, heightMap[m - 1][i]});
            vis[0][i] = vis[m - 1][i] = true;
        }
        // 初始化左右边界上的每个点的路径高度
        for (int i = 1; i < m - 1; i++) {
            q.add(new int[]{i, 0, heightMap[i][0]});
            q.add(new int[]{i, n - 1, heightMap[i][n - 1]});
            vis[i][0] = vis[i][n - 1] = true;
        }
        // 定义4个方向
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int ans = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0], y = poll[1], h = poll[2];
            // 循环处理点的4个方向的每个邻接点
            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                // 如果点超出边界，则不处理
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                // 如果点已经处理过，跳过
                if (vis[nx][ny]) continue;
                if (h > heightMap[nx][ny]) ans += h - heightMap[nx][ny];
                // 设置当前点的路径高度
                q.add(new int[]{nx, ny, Math.max(heightMap[nx][ny], h)});
                vis[nx][ny] = true;
            }
        }
        return ans;
    }

}
