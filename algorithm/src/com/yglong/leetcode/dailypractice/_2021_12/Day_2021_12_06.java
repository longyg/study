package com.yglong.leetcode.dailypractice._2021_12;

/**
 * 1816. 截断句子
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
 * <p>
 * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
 * 给你一个句子 s​​​​​​ 和一个整数 k​​​​​​ ，请你将 s​​ 截断 ​，​​​使截断后的句子仅含 前 k​​​​​​ 个单词。返回 截断 s​​​​​​ 后得到的句子。
 * <p>
 * <p>
 * 连接：https://leetcode-cn.com/problems/truncate-sentence/
 */
public class Day_2021_12_06 {

    public static void main(String[] args) {
        System.out.println(truncateSentence("Hello how are you Contestant", 4));
    }

    public static String truncateSentence1(String s, int k) {
        String[] ss = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(ss[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String truncateSentence2(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                k--;
            }
            if (k <= 0) break;
            sb.append(c);
        }
        return sb.toString();
    }

    public static String truncateSentence(String s, int k) {
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                k--;
            }
            if (k <= 0) break;
            end = i;
        }
        return s.substring(0, end + 1);
    }
}
