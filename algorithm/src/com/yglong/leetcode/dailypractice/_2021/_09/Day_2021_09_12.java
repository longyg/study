package com.yglong.leetcode.dailypractice._2021._09;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 678. 有效的括号字符串
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string/
 */
public class Day_2021_09_12 {
    public static void main(String[] args) {
        System.out.println(checkValidString("()"));
        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("(*))"));
        System.out.println(checkValidString("(((((()*)(*)*))())())(()())())))((**)))))(()())()"));
    }

    /**
     * 栈
     */
    public static boolean checkValidString(String s) {
        Deque<Integer> lq = new LinkedList<>();
        Deque<Integer> sq = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                lq.push(i); // 入栈
            } else if (c == '*') {
                sq.push(i);
            } else {
                if (!lq.isEmpty()) {
                    lq.pop();
                } else if (!sq.isEmpty()) {
                    sq.pop();
                } else {
                    return false;
                }
            }
        }
        while (!lq.isEmpty() && !sq.isEmpty()) {
            if (lq.pop() > sq.pop()) {
                return false;
            }
        }
        return lq.isEmpty();
    }
}
