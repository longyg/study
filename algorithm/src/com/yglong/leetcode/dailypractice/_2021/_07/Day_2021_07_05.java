package com.yglong.leetcode.dailypractice._2021._07;

import java.util.*;

/**
 * 726. 原子的数量
 * <p>
 * <p>
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 * <p>
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * <p>
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，
 * 但 H1O2 这个表达是不可行的。
 * <p>
 * 两个化学式连在一起是新的化学式。例如H2O2He3Mg4 也是化学式。
 * <p>
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * <p>
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），
 * 然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * <p>
 * 输入:
 * <p>
 * formula = "K4(ON(SO3)2)2"
 * <p>
 * 输出: "K4N2O14S4"
 * <p>
 * 解释:
 * <p>
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-atoms
 */
public class Day_2021_07_05 {

    public static void main(String[] args) {
        System.out.println(countOfAtoms("K4(ON(SO3)2)2"));
    }

    /**
     * 栈 + 哈希表
     */
    public static String countOfAtoms(String formula) {
        int i = 0;
        int n = formula.length();
        Deque<Map<String, Integer>> stack = new LinkedList<>();
        stack.push(new HashMap<>());
        while (i < n) {
            char ch = formula.charAt(i);
            i++;
            if (ch == '(') { // 遇到（，压入一个新map
                stack.push(new HashMap<>());
            } else if (ch == ')') { // 遇到），弹出栈顶map，计算内层结果并合并到栈顶map，即外层
                int num = parseNum(i, n, formula);
                Map<String, Integer> popMap = stack.pop();
                Map<String, Integer> topMap = stack.peek();
                popMap.replaceAll((k, v) -> v * num);
                popMap.forEach((k, v) -> {
                    topMap.put(k, topMap.getOrDefault(k, 0) + v);
                });
                i += num == 1 ? 0 : String.valueOf(num).length();
            } else {
                String a = parseAtom(i, n, formula);
                String atom = ch + a;
                i += a.length();
                int num = parseNum(i, n, formula);
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num);
                i += num == 1 ? 0 : String.valueOf(num).length();
            }
        }
        Map<String, Integer> map = stack.pop();
        TreeMap<String, Integer> sortedMap = new TreeMap<>(map);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey());
            int count = entry.getValue();
            if (count > 1) {
                sb.append(count);
            }
        }
        return sb.toString();
    }

    private static int parseNum(int i, int n, String formula) {
        char ch = formula.charAt(i);
        if (i >= n || !isNum(ch)) {
            return 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ch);
        i++;
        while (i < n) {
            char c = formula.charAt(i);
            if (isNum(c)) {
                sb.append(c);
            } else {
                break;
            }
            i++;
        }
        return Integer.parseInt(sb.toString());
    }

    private static String parseAtom(int i, int n, String formula) {
        if (i >= n) {
            return "";
        }
        char ch = formula.charAt(i);
        StringBuilder sb = new StringBuilder();
        if (!isAtom(ch)) {
            return "";
        } else {
            sb.append(ch);
        }
        i++;
        while (i < n) {
            char c = formula.charAt(i);
            if (isAtom(c)) {
                sb.append(c);
            } else {
                break;
            }
            i++;
        }
        return sb.toString();
    }

    private static boolean isAtom(char ch) {
        return ch >= 97 && ch <= 122;
    }

    private static boolean isNum(char ch) {
        return ch >= 48 && ch <= 57;
    }
}
