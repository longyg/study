package com.yglong.leetcode.dailypractice._2021._08;

import java.util.Arrays;

/**
 * 743. 网络延迟时间
 * <p>
 * <p>
 * 有 n 个网络节点，标记为1到 n。
 * <p>
 * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点，
 * <p>
 * wi是一个信号从源节点传递到目标节点的时间。
 * <p>
 * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * <p>
 * 输出：2
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * <p>
 * 输出：1
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * <p>
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= k <= n <= 100
 * <p>
 * 1 <= times.length <= 6000
 * <p>
 * times[i].length == 3
 * <p>
 * 1 <= ui, vi <= n
 * <p>
 * ui != vi
 * <p>
 * 0 <= wi <= 100
 * <p>
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/network-delay-time
 */
public class Day_2021_08_02 {
    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}, {2, 1, 3}}, 2, 2));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2,}, {1, 3, 2}}, 3, 1));
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        // 二维数组g保存所有边的长度
        // 如果两个节点不相连，距离为无穷大，这里用一个很大的值表示无穷大
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], INF);
        }
        for (int[] time : times) {
            int x = time[0] - 1, y = time[1] - 1;
            g[x][y] = time[2];
        }

        // dist数组保存节点k到其他节点的最短距离
        int[] dist = new int[n];
        //初始化k节点到其他节点的最短距离为无穷大，这里用一个很大的值表示无穷大
        Arrays.fill(dist, INF);
        // k节点自己到自己的距离为0
        dist[k - 1] = 0;
        // used数组保存已确定的节点
        boolean[] used = new boolean[n];
        //遍历n次，每次确定一个节点
        for (int i = 0; i < n; ++i) {
            // 找到未确定节点中距离k最短的节点，暂存到x变量
            int x = -1;
            for (int y = 0; y < n; ++y) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            // 将当前最短节点加入已确定节点列表中
            used[x] = true;
            // 更新以x节点为跳转节点，从k到其他节点的最短距离
            for (int y = 0; y < n; ++y) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        int ret = Arrays.stream(dist).max().getAsInt();
        return ret == INF ? -1 : ret;
    }
}
