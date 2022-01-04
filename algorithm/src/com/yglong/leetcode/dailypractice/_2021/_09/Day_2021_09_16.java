package com.yglong.leetcode.dailypractice._2021._09;

import java.util.*;

/**
 * 212. 单词搜索 II
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-search-ii/
 */
public class Day_2021_09_16 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Day_2021_09_16().findWords(new char[][]{{'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain", "khta"}).toArray()));
        System.out.println(Arrays.toString(new Day_2021_09_16().findWords(new char[][]{{'a', 'b'}, {'c', 'd'}}, new String[]{"abcd"}).toArray()));
    }

    private char[][] board;
    private int m, n;
    private boolean[][] vis;
    private final Set<String> set = new HashSet<>();
    private final List<String> ans = new ArrayList<>();
    private final static int MAX_WORD_LEN = 10; // 单词最大长度
    private final int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 回溯
     */
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        vis = new boolean[m][n];

        set.addAll(Arrays.asList(words));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = true;
                sb.append(board[i][j]);
                dfs(sb, i, j);
                vis[i][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return ans;
    }

    private void dfs(StringBuilder sb, int i, int j) {
        // 如果在board中走过的路径长度已经超过了单词最大长度，则不用再继续了
        if (sb.length() > MAX_WORD_LEN) return;
        // 如果所有单词都查找过了，则不用再遍历board了
        if (set.size() < 1) return;
        if (set.contains(sb.toString())) {
            ans.add(sb.toString());
            set.remove(sb.toString());
        }
        for (int[] dir : dirs) {
            int ix = i + dir[0];
            int jx = j + dir[1];
            if (ix < 0 || ix >= m || jx < 0 || jx >= n) continue;
            if (vis[ix][jx]) continue;
            vis[ix][jx] = true;
            sb.append(board[ix][jx]);
            // 深度遍历相邻单元格
            dfs(sb, ix, jx);
            vis[ix][jx] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
