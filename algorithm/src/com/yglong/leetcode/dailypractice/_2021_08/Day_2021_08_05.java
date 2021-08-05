package com.yglong.leetcode.dailypractice._2021_08;

import java.util.*;

/**
 * 802. 找到最终的安全状态
 * <p>
 * <p>
 * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
 * <p>
 * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
 * <p>
 * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
 * <p>
 * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是graph的节点数。
 * <p>
 * 图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * <p>
 * 输出：[2,4,5,6]
 * <p>
 * 解释：示意图如上。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * <p>
 * 输出：[4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == graph.length
 * <p>
 * 1 <= n <= 104
 * <p>
 * 0 <= graph[i].length <= n
 * <p>
 * graph[i] 按严格递增顺序排列。
 * <p>
 * 图中可能包含自环。
 * <p>
 * 图中边的数目在范围 [1, 4 * 104] 内。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-eventual-safe-states
 */
public class Day_2021_08_05 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Day_2021_08_05().eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}).toArray()));
        System.out.println(Arrays.toString(new Day_2021_08_05().eventualSafeNodes(new int[][]{{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}}).toArray()));
        System.out.println(Arrays.toString(new Day_2021_08_05().eventualSafeNodes(new int[][]{{}, {0, 2, 3, 4}, {3}, {4}, {}}).toArray()));
    }

    /**
     * 注意本题的描述，可能会导致误解为，只要能从一个节点到达终点，就是安全的，但实际上并不是这样的。
     * <p>
     * 题目中有一句很关键的话：无论每一步选择沿哪条有向边行走。
     * <p>
     * <p>
     * 有一个关键词“无论”，所以应该要保证，从起始节点出发的所有有向边中，不能构成环，只要有任意一条路径构成了环，那么这个点就不是安全的。
     */
    private List<Integer> result = new ArrayList<>();

    public List<Integer> eventualSafeNodes(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            // pass保存已经过的点
            Set<Integer> pass = new HashSet<>();
            boolean safe = check(graph, i, pass);
            if (safe) {
                result.add(i);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * 深度优先搜索
     */
    private boolean check(int[][] graph, int n, Set<Integer> pass) {
        // 如果节点已经经过，直接返回
        if (pass.contains(n)) {
            return false;
        }
        int[] g = graph[n];
        pass.add(n);
        for (int j : g) {
            boolean safe = check(graph, j, pass);
            if (!safe) {
                return false;
            }
        }
        pass.removeIf(k -> k == n);
        return true;
    }

    /**
     * 官方解法1
     */
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean safe(int[][] graph, int[] color, int i) {
        if (color[i] > 0) {
            return color[i] == 2;
        }
        color[i] = 1;
        for (int k : graph[i]) {
            if (!safe(graph, color, k)) {
                return false;
            }
        }
        color[i] = 2;
        return true;
    }
}
