package com.yglong.leetcode.dailypractice._2021_09;

import java.util.ArrayList;
import java.util.List;

/**
 * 36. 有效的数独
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-sudoku/
 */
public class Day_2021_09_17 {

    public static void main(String[] args) {
        // true
        System.out.println(isValidSudoku(new char[][]{
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                }
        ));
        // false
        System.out.println(isValidSudoku(new char[][]{
                        {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                }
        ));
    }

    public static boolean isValidSudoku(char[][] board) {

        // 判断所有行和列
        for (int i = 0; i < 9; i++) {
            List<Character> rows = new ArrayList<>();
            List<Character> cols = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (rows.contains(board[i][j])) return false;
                    rows.add(board[i][j]);
                }

                if (board[j][i] != '.') {
                    if (cols.contains(board[j][i])) return false;
                    cols.add(board[j][i]);
                }
            }
        }

        // 判断所有3x3宫格
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isValid(board, i, j)) return false;
            }
        }

        return true;
    }

    private static boolean isValid(char[][] board, int i, int j) {
        List<Character> nums = new ArrayList<>();
        for (int k = i * 3; k < i * 3 + 3; k++) {
            for (int l = j * 3; l < j * 3 + 3; l++) {
                if (board[k][l] == '.') continue;
                if (nums.contains(board[k][l])) return false;
                nums.add(board[k][l]);
            }
        }
        return true;
    }
}
