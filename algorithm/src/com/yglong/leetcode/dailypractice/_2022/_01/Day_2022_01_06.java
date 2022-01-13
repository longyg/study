package com.yglong.leetcode.dailypractice._2022._01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 71. 简化路径
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * <p>
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * <p>
 * 始终以斜杠 '/' 开头。
 * <p>
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * <p>
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * <p>
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * <p>
 * 返回简化后得到的 规范路径 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/simplify-path
 */
public class Day_2022_01_06 {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/."));
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
        System.out.println(simplifyPath("/a/../../b/../c//.//"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/a///.//b/////../c/////"));
        System.out.println(simplifyPath("/home/"));
    }

    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        int n = path.length();
        for (int i = 1; i < n; ) {
            if (path.charAt(i) == '/' && ++i >= 0) continue;
            int j = i + 1;
            while (j < n && path.charAt(j) != '/') j++;
            String item = path.substring(i, j);
            if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pollLast();
            } else if (!item.equals(".")) {
                stack.addLast(item);
            }
            i = j;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append("/").append(stack.pollFirst());
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
