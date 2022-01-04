package com.yglong.leetcode.dailypractice._2021._12;

import java.util.*;

/**
 * 794. 有效的井字游戏
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 用字符串数组作为井字游戏的游戏板 board。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。
 * <p>
 * 该游戏板是一个 3 x 3 数组，由字符 " "，"X" 和 "O" 组成。字符 " " 代表一个空位。
 * <p>
 * 以下是井字游戏的规则：
 * <p>
 * 玩家轮流将字符放入空位（" "）中。
 * <p>
 * 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。
 * <p>
 * “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * <p>
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * <p>
 * 当所有位置非空时，也算为游戏结束。
 * <p>
 * 如果游戏结束，玩家不允许再放置字符。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
 */
public class Day_2021_12_09 {

    public static void main(String[] args) {
        System.out.println(new Day_2021_12_09().validTicTacToe(new String[]{"XXO", "XOX", "OXO"})); // false
        System.out.println(new Day_2021_12_09().validTicTacToe(new String[]{"XOX", "OOX", "XO "})); // true
        System.out.println(new Day_2021_12_09().validTicTacToe(new String[]{"XOX", "O X", "X O"})); // true
        System.out.println(new Day_2021_12_09().validTicTacToe(new String[]{"XXX", "XOO", "OO "})); // false
        System.out.println(new Day_2021_12_09().validTicTacToe(new String[]{"O  ", "   ", "   "})); // false
        System.out.println(new Day_2021_12_09().validTicTacToe(new String[]{"XOX", " X ", "   "})); // false
        System.out.println(new Day_2021_12_09().validTicTacToe(new String[]{"XXX", "   ", "OOO"})); // false
        System.out.println(new Day_2021_12_09().validTicTacToe(new String[]{"XOX", "O O", "XOX"})); // true
    }

    static Map<Integer, Map<Integer, Integer>> lines = new HashMap<>();

    static {
        Map<Integer, Integer> m1 = new HashMap<>();
        m1.put(2, 3);
        m1.put(4, 7);
        m1.put(5, 9);
        lines.put(1, m1);
        Map<Integer, Integer> m2 = new HashMap<>();
        m2.put(5, 8);
        lines.put(2, m2);
        Map<Integer, Integer> m3 = new HashMap<>();
        m3.put(5, 7);
        m3.put(6, 9);
        lines.put(3, m3);
        Map<Integer, Integer> m4 = new HashMap<>();
        m4.put(5, 6);
        lines.put(4, m4);
        Map<Integer, Integer> m7 = new HashMap<>();
        m7.put(8, 9);
        lines.put(7, m7);
    }

    public boolean validTicTacToe(String[] board) {
        int x = 0, o = 0;
        List<Integer> xPos = new ArrayList<>();
        List<Integer> oPos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int pos = i * 3 + j + 1;
                char c = board[i].charAt(j);
                if (c == 'X') {
                    x++;
                    xPos.add(pos);
                } else if (c == 'O') {
                    o++;
                    oPos.add(pos);
                }
            }
        }
        if (x >= 3) {
            if (isLine(oPos)) {
                return !isLine(xPos) && x == o;
            }
            if (isLine(xPos)) {
                return x == o + 1;
            }
            if (x + o == 9) {
                return x == o + 1;
            }
        }
        return x == o || x == o + 1;
    }

    private boolean isLine(List<Integer> pos) {
        pos.sort(Comparator.comparingInt(a -> a));
        for (int i = 0; i < pos.size() - 2; i++) {
            for (int j = i + 1; j < pos.size() - 1; j++) {
                for (int k = j + 1; k < pos.size(); k++) {
                    int p1 = pos.get(i);
                    int p2 = pos.get(j);
                    int p3 = pos.get(k);

                    if (lines.containsKey(p1)) {
                        if (lines.get(p1).containsKey(p2)) {
                            if (lines.get(p1).get(p2).equals(p3)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
