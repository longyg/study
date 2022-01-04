package com.yglong.leetcode.dailypractice._2021._10;

import java.util.*;

/**
 * 500. 键盘行
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/keyboard-row/
 */
public class Day_2021_10_31 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
        System.out.println(Arrays.toString(findWords(new String[]{"adsdf", "sfd"})));
    }


    public static String[] findWords(String[] words) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : "qwertyuiop".toCharArray()) {
            m.put(c, 1);
        }
        for (char c : "asdfghjkl".toCharArray()) {
            m.put(c, 2);
        }
        for (char c : "zxcvbnm".toCharArray()) {
            m.put(c, 3);
        }
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            int line = -1;
            boolean same = true;
            for (char c : word.toLowerCase().toCharArray()) {
                int curLine = m.get(c);
                if (line != -1 && line != curLine) {
                    same = false;
                    break;
                }
                line = curLine;
            }
            if (same) {
                ans.add(word);
            }
        }
        String[] ret = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }
}
