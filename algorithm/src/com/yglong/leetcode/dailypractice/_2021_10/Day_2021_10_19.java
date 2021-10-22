package com.yglong.leetcode.dailypractice._2021_10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
 */
public class Day_2021_10_19 {
    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.search("a");
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        System.out.println(wd.search("pad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search("b.."));
    }


    /**
     * 方案一：
     * <p>
     * 把相同长度的word放到同一个HashSet里
     * <p>
     * 在查找时，只需要遍历长度相同的单词
     */
    static class WordDictionary1 {
        private Map<Integer, Set<String>> c = new HashMap<>();

        public WordDictionary1() {
        }

        public void addWord(String word) {

            Set<String> set = c.getOrDefault(word.length(), new HashSet<>());
            set.add(word);
            c.put(word.length(), set);
        }

        public boolean search(String word) {
            if (c.get(word.length()) == null) return false;
            for (String s : c.get(word.length())) {
                if (match(s, word)) {
                    return true;
                }
            }
            return false;
        }

        private boolean match(String s, String word) {
            if (s.length() != word.length()) return false;
            for (int i = 0; i < s.length(); i++) {
                if (word.charAt(i) == '.') continue;
                if (word.charAt(i) != s.charAt(i)) return false;
            }
            return true;
        }
    }


    /**
     * 方案二：
     * <p>
     * 利用前缀树
     */
    static class WordDictionary {
        private TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            root.insert(word);
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int index, TrieNode node) {
            if (index == word.length()) return node.isEnd();
            char c = word.charAt(index);
            if (c != '.') {
                int childIndex = c - 'a';
                TrieNode child = node.getChildren()[childIndex];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            } else {
                // 如果是“.”，则应该搜索所有子节点,最多26个。
                for (int i = 0; i < 26; i++) {
                    TrieNode child = node.getChildren()[i];
                    if (child != null && dfs(word, index + 1, child)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


    /**
     * 前缀树数据结构的实现
     * <p>
     * 对于只包含小写字母的前缀树，每个节点的子节点最多只有26个
     */
    static class TrieNode {
        private boolean isEnd;
        private TrieNode[] children = new TrieNode[26];

        public void insert(String word) {
            TrieNode node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public TrieNode[] getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

}
