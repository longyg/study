package com.yglong.leetcode.dailypractice._2021._09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/
 */
public class Day_2021_09_14 {
    public static void main(String[] args) {
        System.out.println(findLongestWord("abpcplea", new ArrayList<>(Arrays.asList("ale", "apple", "monkey", "plea"))));
        System.out.println(findLongestWord("abpcplea", new ArrayList<>(Arrays.asList("a", "b", "c"))));
        System.out.println(findLongestWord("abce", new ArrayList<>(Arrays.asList("abe", "abc"))));
    }

    /**
     * 排序 + 匹配
     */
    public static String findLongestWord(String s, List<String> dictionary) {
        // 对字典排序
        dictionary.sort((o1, o2) -> {
            // 如果长度不等，按长度从大到小排序
            if (o1.length() != o2.length()) return o2.length() - o1.length();
            // 如果长度相等，按字典序排序
            return o1.compareTo(o2);
        });
        for (String v : dictionary) {
            if (match(v, s)) {
                return v;
            }
        }
        return "";
    }

    /**
     * 字符串匹配
     */
    private static boolean match(String src, String dest) {
        int pos = -1;
        // 判断src字符串中的每个字符是否存在于dest字符串中，并且顺序一致
        for (int i = 0; i < src.length(); i++) {
            pos = dest.indexOf(src.charAt(i), pos + 1);
            if (pos < 0) {
                return false;
            }
        }
        return true;
    }
}
