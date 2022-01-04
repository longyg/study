package com.yglong.leetcode.dailypractice._2021._06;

import java.util.*;

/**
 * 815. 公交路线
 * <p>
 * <p>
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * <p>
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * <p>
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * <p>
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * <p>
 * 输出：2
 * <p>
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * <p>
 * 输出：-1
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/bus-routes
 */
public class Day_2021_06_28 {
    public static void main(String[] args) {
        System.out.println(numBusesToDestination2(new int[][]{{1, 2, 7}, {3, 6, 7}}, 1, 6));
//        System.out.println(numBusesToDestination(new int[][]{{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}},
//                15, 12));
    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        if (notIn(routes, source) || notIn(routes, target)) { // 不在任何线路
            return -1;
        }

        if (inSame(routes, source, target)) { // 在同一公交线路
            return 1;
        }

        Queue<int[]> queue = new LinkedList<>();
        List<int[]> done = new ArrayList<>();
        for (int[] route : routes) {
            if (in(route, source)) {
                queue.offer(route); // 找到所有可乘线路
                done.add(route);
            }
        }
        if (queue.size() == 0) {
            return -1;
        }

        int step = 1;
        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] route = queue.poll();
                for (int[] newRoute : getTransferableRoute(routes, done, route)) {
                    if (in(newRoute, target)) {
                        return step;
                    }
                    queue.offer(newRoute);
                    done.add(newRoute);
                }
            }
        }
        return -1;
    }

    private static boolean contains(List<int[]> routes, int[] route) {
        for (int[] r : routes) {
            if (same(r, route)) {
                return true;
            }
        }
        return false;
    }

    private static boolean same(int[] r1, int[] r2) {
        if (r1.length != r2.length) {
            return false;
        }
        for (int i = 0; i < r1.length; i++) {
            if (r1[i] != r2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 找到某公交路线可转乘的所有路线
     */
    private static List<int[]> getTransferableRoute(int[][] routes, List<int[]> done, int[] route) {
        List<int[]> ret = new ArrayList<>();

        for (int[] r : routes) {
            if (!same(r, route) && notIn(done, r) && transferable(r, route)) {
                ret.add(r);
            }
        }

        return ret;
    }

    private static boolean notIn(List<int[]> routes, int[] route) {
        for (int[] r : routes) {
            if (same(r, route)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两条线路是否可转乘，即判断是否有相同站点
     */
    private static boolean transferable(int[] r1, int[] r2) {
        for (int i : r1) {
            for (int j : r2) {
                if (i == j) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean notIn(int[][] routes, int station) {
        for (int[] route : routes) {
            for (int s : route) {
                if (station == s) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean inSame(int[][] routes, int source, int target) {
        for (int[] route : routes) {
            if (in(route, source) && in(route, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean in(int[] route, int s) {
        for (int i : route) {
            if (s == i) {
                return true;
            }
        }
        return false;
    }


    /**
     * 官方解法
     */
    public static int numBusesToDestination2(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> rec = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = rec.getOrDefault(site, new ArrayList<Integer>());
                for (int j : list) {
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site, list);
            }
        }

        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> que = new LinkedList<Integer>();
        for (int bus : rec.getOrDefault(source, new ArrayList<Integer>())) {
            dis[bus] = 1;
            que.offer(bus);
        }
        while (!que.isEmpty()) {
            int x = que.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    que.offer(y);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int bus : rec.getOrDefault(target, new ArrayList<Integer>())) {
            if (dis[bus] != -1) {
                ret = Math.min(ret, dis[bus]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
