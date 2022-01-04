package com.yglong.leetcode.dailypractice._2021._08;

import java.util.*;

/**
 * 787. K 站中转内最便宜的航班
 * <p>
 * <p>
 * 有 n 个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 toi 抵达 pricei。
 * <p>
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。
 * 如果不存在这样的路线，则输出 -1。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入:
 * <p>
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * <p>
 * src = 0, dst = 2, k = 1
 * <p>
 * 输出: 200
 * <p>
 * 解释:
 * <p>
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入:
 * <p>
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * <p>
 * src = 0, dst = 2, k = 0
 * <p>
 * 输出: 500
 * <p>
 * 解释:
 * <p>
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= n <= 100
 * <p>
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * <p>
 * flights[i].length == 3
 * <p>
 * 0 <= fromi, toi < n
 * <p>
 * fromi != toi
 * <p>
 * 1 <= pricei <= 104
 * <p>
 * 航班没有重复，且不存在自环
 * <p>
 * 0 <= src, dst, k < n
 * <p>
 * src != dst
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
 */
public class Day_2021_08_24 {
    public static void main(String[] args) {
        System.out.println(findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
    }

    /**
     * 广度优先搜索
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // int[] {i, n, price}, 表示从第i站中转，已经中转了n次的最低价格
        Queue<int[]> queue = new LinkedList<>();
        // 保存所有边
        // 数组的每一个元素是一个List，保存了某个站到其他站的所有边
        // 每个边为一个二元组（dst, price）
        List<int[]>[] edge = new List[n];
        // prices保存了从sr到某个站的最低价格
        int[] prices = new int[n];

        // 初始化
        for (int i = 0; i < n; i++) {
            edge[i] = new ArrayList<>();
            prices[i] = Integer.MAX_VALUE;
        }

        // 初始化每个站的所有边
        for (int[] flight : flights) {
            edge[flight[0]].add(new int[]{flight[1], flight[2]});
        }

        prices[src] = 0;
        // 初始化：从第0站出发，中转0次，价格为0
        queue.add(new int[]{src, 0, prices[src]});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] > k) break; // 如果中转次数大于k了，就没必要继续计算了
            // 遍历当前站的所有边
            for (int[] next : edge[cur[0]]) {
                // 到下一个节点的新的价格
                // 等于到当前节点的价格加上当前节点到下一个节点的价格
                int newPrice = cur[2] + next[1];
                if (prices[next[0]] > newPrice) {
                    // 如果已记录的到下一个节点的价格大于newPrice，则更新为newPrice
                    prices[next[0]] = newPrice;
                    queue.add(new int[]{next[0], cur[1] + 1, newPrice});
                }
            }
        }

        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    /**
     * 动态规划
     */
    public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10000 * 101 + 1;
        // dp[t][i]表示恰好经过t次航班，从src到i的最小花费
        int[][] dp = new int[k + 2][n];
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][src] = 0;
        for (int t = 1; t <= k + 1; t++) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                dp[t][i] = Math.min(dp[t][i], dp[t - 1][j] + cost);
            }
        }
        int ans = INF;
        for (int t = 1; t <= k + 1; t++) {
            ans = Math.min(ans, dp[t][dst]);
        }
        return ans == INF ? -1 : ans;
    }
}
