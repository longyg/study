package com.yglong.leetcode.dailypractice._2021._07;

import java.util.*;

/**
 * 面试题 10.02. 变位词组
 * <p>
 * <p>
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * <p>
 * 注意：本题相对原题稍作修改
 * <p>
 * <p>
 * 示例:
 * <p>
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * <p>
 * 输出:
 * <p>
 * [
 * <p>
 * ["ate","eat","tea"],
 * <p>
 * ["nat","tan"],
 * <p>
 * ["bat"]
 * <p>
 * ]
 * <p>
 * <p>
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * <p>
 * 不考虑答案输出的顺序。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/group-anagrams-lcci
 */
public class Day_2021_07_18 {

    public static void main(String[] args) {
        List<List<String>> result = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(Arrays.toString(result.toArray()));
    }

    /**
     * 哈希表 + 排序解法
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // 哈希表，key为输入字符串排序后的字符串
        // 如果数组中的字符串排序后相同，则加入map中相同key的value中
        Map<String, List<String>> groupMap = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            List<String> list = groupMap.getOrDefault(sortedStr, new ArrayList<>());
            list.add(str);
            groupMap.put(sortedStr, list);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : groupMap.values()) {
            result.add(list);
        }
        return result;
    }
}
