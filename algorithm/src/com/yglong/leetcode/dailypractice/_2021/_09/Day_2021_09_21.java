package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 58. 最后一个单词的长度
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/length-of-last-word/
 */
public class Day_2021_09_21 {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("  fly me   to   the moon  "));
    }

    /**
     * 利用字符串的split方法拆分字符串
     */
    public static int lengthOfLastWord1(String s) {
        String[] ss = s.split("\\s+");
        int n = ss.length;
        return ss[n - 1].length();
    }

    /**
     * 从后往前遍历字符串，获取最后一个单词长度
     */
    public static int lengthOfLastWord2(String s) {
        int wordLen = 0; // 记录单词长度
        int wordCnt = 0; // 记录已遍历单词个数
        boolean isLastSpace = true; // 记录上一个字符是否为空格
        // 从后往前遍历，获取单词的个数和其长度
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != ' ') {
                if (isLastSpace) {
                    wordLen = 1;
                    wordCnt++;
                    isLastSpace = false;
                } else {
                    wordLen++;
                }
            } else {
                isLastSpace = true;
            }
            // 当完整获取到一个单词时，即最后一个单词，结束循环
            if (wordCnt == 1 && c == ' ') {
                break;
            }
        }
        return wordLen;
    }

    /**
     * 从后往前遍历，先去掉最后的空格，再获取最后一个非空格子字符串长度
     */
    public static int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        while (s.charAt(i) == ' '){
            i--;
        }
        int wordLen = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            wordLen++;
            i--;
        }
        return wordLen;
    }
}
