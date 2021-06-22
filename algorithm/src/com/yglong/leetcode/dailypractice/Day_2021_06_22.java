package com.yglong.leetcode.dailypractice;

import java.util.*;

/**
 * 剑指 Offer 38. 字符串的排列
 * <p>
 * <p>
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * <p>
 * 示例:
 * <p>
 * <p>
 * 输入：s = "abc"
 * <p>
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 */
public class Day_2021_06_22 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(permutation2("abc")));
        Day_2021_06_22 t = new Day_2021_06_22();
        System.out.println(Arrays.toString(t.permutation2("abc")));
    }

    /**
     * 暴力递归解法
     */
    public static String[] permutation(String s) {
        if (s.length() == 1) {
            return new String[]{s};
        }
        List<Character> cList = new ArrayList<>();
        for (char c : s.toCharArray()) {
            cList.add(c);
        }
        return permutation(cList);
    }

    private static String[] permutation(List<Character> cList) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < cList.size(); i++) {
            List<String> subResult = new ArrayList<>();
            List<Character> sub = getSubList(cList, i);
            permutation(subResult, cList.get(i).toString(), sub);
            result.addAll(subResult);
        }
        return result.toArray(new String[0]);
    }

    private static List<Character> getSubList(List<Character> cList, int removeIndex) {
        List<Character> sub = new ArrayList<>();
        for (int i = 0; i < cList.size(); i++) {
            if (i != removeIndex) {
                sub.add(cList.get(i));
            }
        }
        return sub;
    }

    private static void permutation(List<String> result, String prefix, List<Character> cList) {
        if (cList.size() == 1) {
            result.add(prefix + cList.get(0));
            return;
        }
        for (int i = 0; i < cList.size(); i++) {
            List<Character> sub = getSubList(cList, i);
            permutation(result, prefix + cList.get(i).toString(), sub);
        }
    }


    List<String> rec;
    boolean[] vis;
    /**
     * 回溯法
     */
    public String[] permutation2(String s) {
        rec = new ArrayList<>();
        int n = s.length();
        vis = new boolean[n];
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        StringBuffer perm = new StringBuffer();
        backtrack(chars, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }

    private void backtrack(char[] chars, int i, int n, StringBuffer perm) {
        if (i == n) {
            rec.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (vis[j] || (j > 0 && !vis[j - 1] && chars[j - 1] == chars[j])) {
                continue;
            }
            vis[j] = true;
            perm.append(chars[j]);
            backtrack(chars, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }
}
