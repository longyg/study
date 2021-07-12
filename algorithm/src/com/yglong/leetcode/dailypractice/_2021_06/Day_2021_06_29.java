package com.yglong.leetcode.dailypractice._2021_06;

import java.util.HashMap;
import java.util.Map;

/**
 * 168. Excel表列名称
 * <p>
 * <p>
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：columnNumber = 1
 * <p>
 * 输出："A"
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：columnNumber = 28
 * <p>
 * 输出："AB"
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：columnNumber = 701
 * <p>
 * 输出："ZY"
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title/
 */
public class Day_2021_06_29 {
    public static void main(String[] args) {
        System.out.println(convertToTitle(702));
        System.out.println(702 / 26);
    }

    private static String COLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String convertToTitle(int columnNumber) {
        Map<Integer, String> map = new HashMap<>();
        char[] chars = COLS.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(i + 1, String.valueOf(chars[i]));
        }
        map.put(0, "Z");

        if (columnNumber <= 26) {
            return map.get(columnNumber);
        }

        return getSub(columnNumber, map);
    }

    public static String getSub(int num, Map<Integer, String> map) {
        int c = num / 26;
        int y = num % 26;

        if (y == 0) {
            if (c <= 26) {
                return map.get(c - 1) + map.get(y);
            } else {
                return getSub(c - 1, map);
            }
        } else {
            if (c <= 26) {
                return map.get(c) + map.get(y);
            } else {
                String sub = getSub(c, map);
                return sub + map.get(y);
            }
        }
    }
}
