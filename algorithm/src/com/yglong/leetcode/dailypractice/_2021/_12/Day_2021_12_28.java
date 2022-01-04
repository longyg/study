package com.yglong.leetcode.dailypractice._2021._12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 472. 连接词
 * <p>
 * <p>
 * 难度：困难
 * <p>
 * <p>
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * <p>
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/concatenated-words
 */
public class Day_2021_12_28 {
    public static void main(String[] args) {
        System.out.println(new Day_2021_12_28().findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}));
        System.out.println(new Day_2021_12_28().findAllConcatenatedWordsInADict(new String[]{"cat", "dog", "catdog", "dogcat", "dogcatdog"}));
        System.out.println(new Day_2021_12_28().findAllConcatenatedWordsInADict(new String[]{"cat", "catcat", "dog"}));
    }

    /**
     * 排序 + 字典树 + 深度优先搜索
     */

    Trie trie = new Trie();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            if (dfs(word, 0)) {
                ans.add(word);
            } else {
                insert(word);
            }
        }
        return ans;
    }

    public boolean dfs(String word, int start) {
        if (word.length() == start) {
            return true;
        }
        Trie node = trie;
        for (int i = start; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            node = node.children[index];
            if (node == null) {
                return false;
            }
            if (node.isEnd) {
                if (dfs(word, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insert(String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    static class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }
}



