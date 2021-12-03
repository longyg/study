package com.yglong.leetcode.dailypractice._2021_12;

/**
 * 1446. 连续字符
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 * <p>
 * 请你返回字符串的能量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "leetcode"
 * <p>
 * 输出：2
 * <p>
 * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "abbcccddddeeeeedcba"
 * <p>
 * 输出：5
 * <p>
 * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "triplepillooooow"
 * <p>
 * 输出：5
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "hooraaaaaaaaaaay"
 * <p>
 * 输出：11
 * <p>
 * <p>
 * 示例 5：
 * <p>
 * 输入：s = "tourist"
 * <p>
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * <p>
 * s 只包含小写英文字母。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/consecutive-characters/
 */
public class Day_2021_12_01 {
    public static void main(String[] args) {
        System.out.println(maxPower("leetcode"));
        System.out.println(maxPower("a"));
    }

    public static int maxPower(String s) {
        int maxLen = 1;
        int cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
                maxLen = Math.max(cnt, maxLen);
            } else {
                cnt = 1;
            }
        }
        return maxLen;
    }

}