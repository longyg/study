package com.yglong.leetcode.dailypractice._2021_07;

/**
 * 171. Excel表列序号
 * <p>
 * <p>
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * <p>
 * B -> 2
 * <p>
 * C -> 3
 * <p>
 * ...
 * <p>
 * Z -> 26
 * <p>
 * AA -> 27
 * <p>
 * AB -> 28
 * <p>
 * ...
 *
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: "A"
 * <p>
 * 输出: 1
 * <p>
 * <p>
 * 示例2:
 * <p>
 * <p>
 * 输入: "AB"
 * <p>
 * 输出: 28
 * <p>
 * <p>
 * 示例3:
 * <p>
 * <p>
 * 输入: "ZY"
 * <p>
 * 输出: 701
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
 */
public class Day_2021_07_30 {

    public static void main(String[] args) {
        System.out.println(titleToNumber2("A"));
        System.out.println(titleToNumber2("AB"));
        System.out.println(titleToNumber2("ZY"));
    }

    public static int titleToNumber(String columnTitle) {
        String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] arr = columnTitle.toCharArray();
        int ret = 0;
        int m = arr.length - 1;
        for (char c : arr) {
            int i = ALPHA.indexOf(c) + 1;
            ret += i * Math.pow(26, m);
            m--;
        }
        return ret;
    }

    public static int titleToNumber2(String columnTitle) {
        int ret = 0;
        int m = columnTitle.length() - 1;
        for (int i = 0; i < columnTitle.length(); i++) {
            int n = columnTitle.charAt(i) - 'A' + 1;
            ret += n * Math.pow(26, m);
            m--;
        }
        return ret;
    }
}
