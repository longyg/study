package com.yglong.leetcode.dailypractice._2021._08;

import java.util.*;

/**
 * 797. 所有可能的路径
 * <p>
 * <p>
 * 给你一个有n个节点的 有向无环图（DAG），请你找出所有从节点 0到节点 n-1的路径并输出（不要求按特定顺序）
 * <p>
 * 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
 * <p>
 * 译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：graph = [[1,2],[3],[3],[]]
 * <p>
 * 输出：[[0,1,3],[0,2,3]]
 * <p>
 * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * <p>
 * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：graph = [[1],[]]
 * <p>
 * 输出：[[0,1]]
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * <p>
 * 输入：graph = [[1,2,3],[2],[3],[]]
 * <p>
 * 输出：[[0,1,2,3],[0,2,3],[0,3]]
 * <p>
 * <p>
 * 示例 5：
 * <p>
 * <p>
 * 输入：graph = [[1,3],[2],[3],[]]
 * <p>
 * 输出：[[0,1,2,3],[0,3]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == graph.length
 * <p>
 * 2 <= n <= 15
 * <p>
 * 0 <= graph[i][j] < n
 * <p>
 * graph[i][j] != i（即，不存在自环）
 * <p>
 * graph[i] 中的所有元素 互不相同
 * <p>
 * 保证输入为 有向无环图（DAG）
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
 */
public class Day_2021_08_25 {

    public static void main(String[] args) {
        Day_2021_08_25 t = new Day_2021_08_25();
        System.out.println(t.allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
    }

    List<List<Integer>> ret = new ArrayList<>();

    /**
     * 深度优先搜索
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, n, graph, list);
        return ret;
    }

    private void dfs(int index, int n, int[][] graph, List<Integer> list) {
        if (index == n - 1) {
            ret.add(list);
            return;
        }
        for (int next : graph[index]) {
            List<Integer> l = new ArrayList<>(list);
            l.add(next);
            dfs(next, n, graph, l);
        }
    }
}
