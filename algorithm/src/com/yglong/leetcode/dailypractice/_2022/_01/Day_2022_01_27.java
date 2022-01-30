package com.yglong.leetcode.dailypractice._2022._01;

import java.util.regex.Pattern;

/**
 * 2047. 句子中的有效单词数
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * <p>
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
 * <p>
 * 仅由小写字母、连字符和/或标点（不含数字）。
 * <p>
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * <p>
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * <p>
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * <p>
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence
 */
public class Day_2022_01_27 {

    public static void main(String[] args) {
        System.out.println(countValidWords("  r3  6bb!f el49 jq.law3  q vju5dg0 .mcxq54jjz a6 n az 8 9bbxyivnrbb g .c8  d e xy29upl var b  7! yqs z 10m t qm  .t3i8e2lp3- xf d pd.   t yy9rk4y, 8, 7 mxl-sn-n  etk.5n va d.pym3..ri0 g9a.dgz0k 5qqxs!a s    46csnc u ima p    "));
        System.out.println(countValidWords(". ! 7hk  al6 l! aon49esj35la k3 7u2tkh  7i9y5  !jyylhppd et v- h!ogsouv 5"));
        System.out.println(countValidWords("a"));
        System.out.println(countValidWords("cat and  dog"));
        System.out.println(countValidWords("alice and  bob are playing stone-game10"));
        System.out.println(countValidWords("!this  1-s b8d!"));
        System.out.println(countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."));
    }

    public static int countValidWords(String sentence) {
        String[] tokens = sentence.split("\\s+");
        Pattern p = Pattern.compile("^[a-z]?([a-z]+-?[a-z]+)?[!.,]?$");
        int ans = 0;
        for (String token : tokens) {
            token = token.trim();
            if (token.equals("")) continue;
            if (p.matcher(token).matches()) {
                ans++;
            }
        }
        return ans;
    }
}
