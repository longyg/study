package com.yglong.leetcode.dailypractice._2022._01;

/**
 * 1614. 括号的最大嵌套深度
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 如果字符串满足以下条件之一，则可以称之为 有效括号字符串（valid parentheses string，可以简写为 VPS）：
 * <p>
 * 字符串是一个空字符串 ""，或者是一个不为 "(" 或 ")" 的单字符。
 * <p>
 * 字符串可以写为 AB（A 与 B 字符串连接），其中 A 和 B 都是 有效括号字符串 。
 * <p>
 * 字符串可以写为 (A)，其中 A 是一个 有效括号字符串 。
 * <p>
 * 类似地，可以定义任何有效括号字符串 S 的 嵌套深度 depth(S)：
 * <p>
 * <p>
 * depth("") = 0
 * <p>
 * depth(C) = 0，其中 C 是单个字符的字符串，且该字符不是 "(" 或者 ")"
 * <p>
 * depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 有效括号字符串
 * <p>
 * depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 有效括号字符串
 * <p>
 * 例如：""、"()()"、"()(()())" 都是 有效括号字符串（嵌套深度分别为 0、1、2），而 ")(" 、"(()" 都不是 有效括号字符串 。
 * <p>
 * 给你一个 有效括号字符串 s，返回该字符串的 s 嵌套深度 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses
 */
public class Day_2022_01_07 {
    public static void main(String[] args) {
        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
        System.out.println(maxDepth("(1)+((2))+(((3)))"));
        System.out.println(maxDepth("1+(2*3)/(2-1)"));
        System.out.println(maxDepth("1"));
    }

    public static int maxDepth(String s) {
        int size = 0, depth = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                size++;
                depth = Math.max(depth, size);
            } else if (c == ')') {
                size--;
            }
        }
        return depth;
    }
}
