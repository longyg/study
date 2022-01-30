package com.yglong.leetcode.dailypractice._2022._01;

import java.util.*;

/**
 * 884. 两句话中的不常见单词
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * <p>
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * <p>
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/uncommon-words-from-two-sentences
 */
public class Day_2022_01_30 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(uncommonFromSentences("this apple is sweet", "this apple is sour")));
    }

    public static String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> m = new HashMap<>();
        List<String> ans = new ArrayList<>();
        String[] list = s1.split(" ");
        for (String s : list) {
            m.put(s, m.getOrDefault(s, 0) + 1);
        }
        list = s2.split(" ");
        for (String s : list) {
            m.put(s, m.getOrDefault(s, 0) + 1);
        }
        m.forEach((k, v) -> {
            if (v == 1) {
                ans.add(k);
            }
        });
        return ans.toArray(new String[0]);
    }
}
