package com.yglong.leetcode.dailypractice._2021._12;

import java.util.HashMap;
import java.util.Map;

/**
 * 748. 最短补全词
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
 * <p>
 * 补全词 是一个包含 licensePlate 中所有的字母的单词。在所有补全词中，最短的那个就是 最短补全词 。
 * <p>
 * 在匹配 licensePlate 中的字母时：
 * <p>
 * 忽略 licensePlate 中的 数字和空格 。
 * <p>
 * 不区分大小写。
 * <p>
 * 如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
 * <p>
 * <p>
 * 例如：licensePlate = "aBc 12c"，那么它的补全词应当包含字母 'a'、'b' （忽略大写）和两个 'c' 。可能的 补全词 有 "abccdef"、"caaacab" 以及 "cbca" 。
 * <p>
 * 请你找出并返回 words 中的 最短补全词 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 words 中 最靠前的 那个。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/shortest-completing-word
 */
public class Day_2021_12_10 {

    public static void main(String[] args) {
        System.out.println(shortestCompletingWord("Ah71752", new String[]{"suggest", "letter", "of", "husband", "easy", "education", "drug", "prevent", "writer", "old"}));
    }

    /**
     * 哈希表
     */
    public static String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character, Integer> plateMap = new HashMap<>();
        for (char c : licensePlate.toCharArray()) {
            if (!Character.isLetter(c)) {
                continue;
            }
            c = Character.toLowerCase(c);
            plateMap.put(c, plateMap.getOrDefault(c, 0) + 1);
        }
        int minLen = 15; // 题目中words中的单词最大长度是15
        String ans = null;
        Map<Character, Integer> m = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                c = Character.toLowerCase(c);
                m.put(c, m.getOrDefault(c, 0) + 1);
            }
            boolean match = true;
            for (Character key : plateMap.keySet()) {
                if (!m.containsKey(key) || m.get(key) < plateMap.get(key)) {
                    match = false;
                    break;
                }
            }
            if (match && minLen > word.length()) {
                minLen = word.length();
                ans = word;
            }
            m.clear();
        }
        return ans;
    }
}
