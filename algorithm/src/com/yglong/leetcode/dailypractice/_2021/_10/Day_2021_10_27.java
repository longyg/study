package com.yglong.leetcode.dailypractice._2021._10;

import java.util.*;

/**
 * 301. 删除无效的括号
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses/
 */
public class Day_2021_10_27 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeInvalidParentheses("(a)())()").toArray()));
    }

    /**
     * 广度优先搜索
     */
    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> currSet = new HashSet<>();

        currSet.add(s);
        while (true) {
            for (String str : currSet) {
                if (isValid(str)) {
                    ans.add(str);
                }
            }
            if (ans.size() > 0) {
                return ans;
            }
            Set<String> nextSet = new HashSet<>();
            for (String str : currSet) {
                for (int i = 0; i < str.length(); i++) {
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                        continue;
                    }
                    if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                    }
                }
            }
            currSet = nextSet;
        }
    }

    private static boolean isValid(String str) {
        char[] ss = str.toCharArray();
        int count = 0;
        for (char c : ss) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }
}
