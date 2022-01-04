package com.yglong.leetcode.dailypractice._2021._11;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * <p>
 * 输出: [0,6]
 * <p>
 * 解释:
 * <p>
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * <p>
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * <p>
 *  示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * <p>
 * 输出: [0,1,2]
 * <p>
 * 解释:
 * <p>
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * <p>
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
public class Day_2021_11_28 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findAnagrams("cbaebabacd", "abc").toArray()));
        System.out.println(Arrays.toString(findAnagrams("abab", "ab").toArray()));
    }

    /**
     * 哈希表
     */
    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        int pLen = p.length();
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i <= n - pLen; i++) {
            if (i == 0) {
                for (int j = 0; j < pLen; j++) {
                    char c = s.charAt(j);
                    m.put(c, m.getOrDefault(c, 0) + 1);
                }
            } else {
                char c1 = s.charAt(i - 1);
                char c2 = s.charAt(i + pLen - 1);
                // 字串向前移动一步
                // 减去或删掉上一个字串的第一个字符
                if (m.get(c1) > 1) {
                    m.put(c1, m.get(c1) - 1);
                } else {
                    m.remove(c1);
                }
                // 添加下一个字符
                m.put(c2, m.getOrDefault(c2, 0) + 1);
            }

            // 比较当前字串与p是否相同
            if (pMap.keySet().size() != m.keySet().size()) continue;
            boolean equals = true;
            for (Character key : pMap.keySet()) {
                if (!m.containsKey(key) || !m.get(key).equals(pMap.get(key))) {
                    equals = false;
                    break;
                }
            }
            if (equals) {
                ans.add(i);
            }
        }
        return ans;
    }
}
