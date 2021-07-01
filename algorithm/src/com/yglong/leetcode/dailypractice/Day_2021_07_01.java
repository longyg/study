package com.yglong.leetcode.dailypractice;

import java.util.*;

/**
 * LCP 07. 传递信息
 * <p>
 * <p>
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 * <p>
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。
 * 返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 * <p>
 * 输出：3
 * <p>
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 * <p>
 * 输出：0
 * <p>
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi
 */
public class Day_2021_07_01 {

    public static void main(String[] args) {
        System.out.println(numWays(5, new int[][]{{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}}, 3));
    }

    /**
     * 广度优先搜索
     */
    public static int numWays(int n, int[][] relation, int k) {
        if (relation.length < 2) {
            return 1;
        }

        // edges保存每个玩家可以传递的其他玩家
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : relation) {
            int src = edge[0], dst = edge[1];
            edges.get(src).add(dst);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        int ways = 0;

        int step = 0;
        while (!queue.isEmpty() && step < k) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer s = queue.poll();
                for (int next : edges.get(s)) {
                    if (next == n - 1 && step == k) {
                        ways++; // 当到达目标，且经过k轮时，即找到一个方案，加1
                    }
                    queue.offer(next);
                }
            }
        }

        return ways;
    }
}
