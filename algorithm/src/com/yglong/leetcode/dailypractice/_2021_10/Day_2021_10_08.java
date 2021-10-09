package com.yglong.leetcode.dailypractice._2021_10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 187. 重复的DNA序列
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences/
 */
public class Day_2021_10_08 {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> m = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            Integer cnt = m.getOrDefault(sub, 0) + 1;
            m.put(sub, cnt);
            // 刚好为2时添加，避免重复添加
            if (m.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }
}
