package com.yglong.leetcode.dailypractice._2022._01;

import java.util.*;

/**
 * 1036. 逃离大迷宫
 * <p>
 * <p>
 * 难度：困难
 * <p>
 * <p>
 * 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为 (x, y) 。
 * <p>
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
 * <p>
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
 * <p>
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/escape-a-large-maze
 */
public class Day_2022_01_11 {
    public static void main(String[] args) {
        System.out.println(new Day_2022_01_11().isEscapePossible(new int[][]{{0, 1}, {1, 0}}, new int[]{0, 0}, new int[]{0, 2}));
        System.out.println(new Day_2022_01_11().isEscapePossible(new int[][]{}, new int[]{0, 0}, new int[]{999999, 999999}));
    }

    int[] source;
    int[] target;
    private static final int n = 1000000;
    Set<P> blocked = new HashSet<>();
    Set<P> accessed = new HashSet<>();

    /**
     * 广度优先搜索，对于相距很远的网格，会超时
     */
    public boolean isEscapePossible1(int[][] bs, int[] source, int[] target) {
        this.source = source;
        this.target = target;
        for (int[] b : bs) {
            this.blocked.add(new P(b[0], b[1]));
        }

        Queue<P> queue = new LinkedList<>();
        P src = new P(source[0], source[1]);
        queue.offer(src);
        while (!queue.isEmpty()) {
            P cur = queue.poll();
            if (cur.x == target[0] && cur.y == target[1]) return true;
            List<P> nexts = new ArrayList<>();
            if (cur.x > 0) nexts.add(new P(cur.x - 1, cur.y));
            if (cur.y > 0) nexts.add(new P(cur.x, cur.y - 1));
            if (cur.x < n) nexts.add(new P(cur.x + 1, cur.y));
            if (cur.y < n) nexts.add(new P(cur.x, cur.y + 1));
            for (P next : nexts) {
                if (!blocked.contains(next) && !accessed.contains(next)) {
                    queue.offer(next);
                }
            }
            accessed.add(cur);
        }
        return false;
    }

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 离散化 + 广度优先搜索
     */
    public boolean isEscapePossible(int[][] bs, int[] source, int[] target) {
        if (bs.length < 2) return true;

        TreeSet<Integer> rows = new TreeSet<>();
        TreeSet<Integer> columns = new TreeSet<>();

        for (int[] b : bs) {
            rows.add(b[0]);
            columns.add(b[1]);
        }

        rows.add(source[0]);
        rows.add(target[0]);
        columns.add(source[1]);
        columns.add(target[1]);

        HashMap<Integer, Integer> rMap = new HashMap<>();
        HashMap<Integer, Integer> cMap = new HashMap<>();

        int firstRow = rows.first();
        int rId = (firstRow == 0 ? 0 : 1);
        rMap.put(firstRow, rId);
        int prevRow = firstRow;
        for (int row : rows) {
            if (row == firstRow) continue;
            rId += (row == prevRow + 1) ? 1 : 2;
            rMap.put(row, rId);
            prevRow = row;
        }
        if (prevRow != n - 1) {
            rId++;
        }

        int firstCol = columns.first();
        int cId = firstCol == 0 ? 0 : 1;
        cMap.put(firstCol, cId);
        int prevCol = firstCol;
        for (int col : columns) {
            if (col == firstCol) continue;
            cId += col == prevCol + 1 ? 1 : 2;
            cMap.put(col, cId);
            prevCol = col;
        }
        if (prevCol != n - 1) {
            cId++;
        }
        int[][] grid = new int[rId + 1][cId + 1];
        for (int[] b : bs) {
            int x = b[0], y = b[1];
            grid[rMap.get(x)][cMap.get(y)] = 1;
        }

        int sx = rMap.get(source[0]), sy = cMap.get(source[1]);
        int tx = rMap.get(target[0]), ty = cMap.get(target[1]);

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});
        grid[sx][sy] = 1;
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0], y = p[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx <= rId && ny >= 0 && ny <= cId && grid[nx][ny] != 1) {
                    if (nx == tx && ny == ty) return true;
                    queue.offer(new int[]{nx, ny});
                    grid[nx][ny] = 1;
                }
            }
        }
        return false;
    }

    static class P {
        private int x;
        private int y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            P p = (P) o;
            return x == p.x &&
                    y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
