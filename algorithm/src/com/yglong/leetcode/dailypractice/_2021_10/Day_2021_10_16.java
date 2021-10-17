package com.yglong.leetcode.dailypractice._2021_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 282. 给表达式添加运算符
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/expression-add-operators/
 */
public class Day_2021_10_16 {

    public static void main(String[] args) {
        Day_2021_10_16 t = new Day_2021_10_16();
        System.out.println(Arrays.toString(t.addOperators("123", 6).toArray()));
    }

    List<String> ans = new ArrayList<>();
    String s;
    int n, t;

    public List<String> addOperators(String num, int target) {
        s = String.valueOf(num);
        n = s.length();
        t = target;
        dfs(0, 0, 0, "");
        return ans;
    }

    /**
     * 深度优先搜索
     *
     * @param u    当前正在决策字符串的第几位
     * @param prev 当前位的前一个数（有可能是乘法的结果）
     * @param cur  当前位置的运算结果
     * @param ss   当前构成的表达式
     */
    private void dfs(int u, long prev, long cur, String ss) {
        if (u == n) {
            if (cur == t) ans.add(ss);
            return;
        }

        for (int i = u; i < n; i++) {
            // 如果当前第一个为0，后续的都不能构成表达式，直接退出循环
            if (i != u && s.charAt(u) == '0') break;
            long next = Long.parseLong(s.substring(u, i + 1));
            if (u == 0) {
                dfs(i + 1, next, next, "" + next);
            } else {
                dfs(i + 1, next, cur + next, ss + "+" + next);
                dfs(i + 1, -next, cur - next, ss + "-" + next);
                long x = prev * next;
                dfs(i + 1, x, cur - prev + x, ss + "*" + next);
            }
        }
    }
}
