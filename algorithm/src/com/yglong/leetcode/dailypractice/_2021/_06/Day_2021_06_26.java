package com.yglong.leetcode.dailypractice._2021._06;


import java.util.*;

/**
 * 773. 滑动谜题
 * <p>
 * <p>
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用0来表示.
 * <p>
 * 一次移动定义为选择0与一个相邻的数字（上下左右）进行交换.
 * <p>
 * 最终当板board的结果是[[1,2,3],[4,5,0]]谜板被解开。
 * <p>
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入：board = [[1,2,3],[4,0,5]]
 * <p>
 * 输出：1
 * <p>
 * 解释：交换 0 和 5 ，1 步完成
 * <p>
 * <p>
 * 输入：board = [[1,2,3],[5,4,0]]
 * <p>
 * 输出：-1
 * <p>
 * 解释：没有办法完成谜板
 * <p>
 * <p>
 * 输入：board = [[4,1,2],[5,0,3]]
 * <p>
 * 输出：5
 * <p>
 * 解释：
 * <p>
 * 最少完成谜板的最少移动次数是 5 ，
 * <p>
 * 一种移动路径:
 * <p>
 * 尚未移动: [[4,1,2],[5,0,3]]
 * <p>
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * <p>
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * <p>
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * <p>
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * <p>
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * <p>
 * 输入：board = [[3,2,4],[1,5,0]]
 * <p>
 * 输出：14
 *
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/sliding-puzzle
 */
public class Day_2021_06_26 {
    private static final int[][] TARGET = new int[][]{{1, 2, 3}, {4, 5, 0}};

    private static final int[][] neighbors = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public static void main(String[] args) {
//        System.out.println(slidingPuzzle(new int[][]{{1, 2, 3}, {5, 4, 0}}));
//        System.out.println(slidingPuzzle(new int[][]{{3, 2, 4}, {1, 5, 0}}));
        System.out.println(slidingPuzzle2(new int[][]{{3, 2, 4}, {1, 5, 0}}));
    }

    /**
     * 广度优先搜索
     */
    public static int slidingPuzzle(int[][] board) {
        if (isTarget(board)) {
            return 0;
        }
        LinkedList<int[][]> queue = new LinkedList<>();
        Set<int[][]> done = new HashSet<>();
        queue.offer(board);
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[][] status = queue.poll();
                for (int[][] newStatus : getPossible(status)) {
                    if (!contains(done, newStatus)) {
                        if (isTarget(newStatus)) {
                            return step;
                        }
                        queue.offer(newStatus);
                        done.add(newStatus);
                    }
                }
            }
        }
        return -1;
    }

    private static boolean equal(int[][] target, int[][] s) {
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[0].length; j++) {
                if (target[i][j] != s[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean contains(Set<int[][]> set, int[][] s) {
        for (int[][] ints : set) {
            if (equal(ints, s)) {
                return true;
            }
        }
        return false;
    }

    private static List<int[][]> getPossible(int[][] status) {
        List<int[][]> list = new ArrayList<>();
        int i = 0, j = 0;
        for (int row = 0; row < status.length; row++) {
            for (int col = 0; col < status[0].length; col++) {
                if (status[row][col] == 0) {
                    i = row;
                    j = col;
                    break;
                }
            }
        }

        List<String> swapDirections = new ArrayList<>();
        if (i == 0) {
            swapDirections.add("D");
        } else if (i == status.length - 1) {
            swapDirections.add("U");
        } else {
            swapDirections.addAll(Arrays.asList("D", "U"));
        }

        if (j == 0) {
            swapDirections.add("R");
        } else if (j == status[0].length - 1) {
            swapDirections.add("L");
        } else {
            swapDirections.addAll(Arrays.asList("R", "L"));
        }

        for (String swapDirection : swapDirections) {
            int newI = i, newJ = j;
            switch (swapDirection) {
                case "L": //左
                    newJ = j - 1;
                    break;
                case "R": //右
                    newJ = j + 1;
                    break;
                case "U": //上
                    newI = i - 1;
                    break;
                case "D": //下
                    newI = i + 1;
                    break;
            }
            swap(list, status, i, j, newI, newJ);
        }

        return list;
    }

    private static void swap(List<int[][]> list, int[][] status, int i, int j, int newI, int newJ) {
        int t = status[newI][newJ];
        status[i][j] = t;
        status[newI][newJ] = 0;
        list.add(copy(status));
        // 恢复原数组
        status[newI][newJ] = t;
        status[i][j] = 0;
    }

    private static int[][] copy(int[][] status) {
        int[][] newStatus = new int[status.length][status[0].length];
        for (int i = 0; i < status.length; i++) {
            newStatus[i] = Arrays.copyOf(status[i], status[i].length);
        }
        return newStatus;
    }

    private static boolean isTarget(int[][] nums) {
        return equal(TARGET, nums);
    }


    /**
     * 简化广度优先搜索
     */
    public static int slidingPuzzle2(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : board) {
            for (int anInt : ints) {
                sb.append(anInt);
            }
        }
        String init = sb.toString();
        if ("123450".equals(init)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(init);
        Set<String> done = new HashSet<>();
        done.add(init);

        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String newStatus : get(status)) {
                    if (!done.contains(newStatus)) {
                        if ("123450".equals(newStatus)) {
                            return step;
                        }
                        queue.offer(newStatus);
                        done.add(newStatus);
                    }
                }
            }
        }

        return -1;
    }

    private static List<String> get(String status) {
        List<String> list = new ArrayList<>();
        char[] chars = status.toCharArray();
        int i = status.indexOf("0");
        for (int j : neighbors[i]) {
            swap(chars, i, j);
            list.add(new String(chars));
            swap(chars, i, j);
        }
        return list;
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
