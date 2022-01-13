package com.yglong.leetcode.dailypractice._2022._01;

import java.util.ArrayList;
import java.util.List;

/**
 * 306. 累加数
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * <p>
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * <p>
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/additive-number
 */
public class Day_2022_01_10 {
    public static void main(String[] args) {
        System.out.println(new Day_2022_01_10().isAdditiveNumber("198019823962"));
        System.out.println(new Day_2022_01_10().isAdditiveNumber("000"));
        System.out.println(new Day_2022_01_10().isAdditiveNumber("1203"));
        System.out.println(new Day_2022_01_10().isAdditiveNumber("101"));
        System.out.println(new Day_2022_01_10().isAdditiveNumber("1023"));
        System.out.println(new Day_2022_01_10().isAdditiveNumber("111"));
        System.out.println(new Day_2022_01_10().isAdditiveNumber("123"));
        System.out.println(new Day_2022_01_10().isAdditiveNumber("112358"));
        System.out.println(new Day_2022_01_10().isAdditiveNumber("199100199"));
    }

    String num;
    int n;
    List<List<Integer>> list = new ArrayList<>();

    /**
     * 深度搜索 + 回溯剪枝
     */
    public boolean isAdditiveNumber(String num) {
        this.num = num;
        n = num.length();
        return dfs(0);
    }

    private boolean dfs(int u) {
        int m = list.size();
        if (u == n) return m >= 3;
        int max = num.charAt(u) == '0' ? u + 1 : n; // 下一个数的最大可能位置
        List<Integer> cur = new ArrayList<>();
        for (int i = u; i < max; i++) {
            cur.add(0, num.charAt(i) - '0');
            if (m < 2 || check(list.get(m - 2), list.get(m - 1), cur)) {
                list.add(cur);
                if (dfs(i + 1)) return true;
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

    private boolean check(List<Integer> a, List<Integer> b, List<Integer> c) {
        List<Integer> ans = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < a.size() || i < b.size(); i++) {
            if (i < a.size()) t += a.get(i);
            if (i < b.size()) t += b.get(i);
            ans.add(t % 10);
            t /= 10;
        }
        if (t > 0) ans.add(t);
        if (c.size() != ans.size()) return false;
        for (int i = 0; i < c.size(); i++) {
            if (!c.get(i).equals(ans.get(i))) {
                return false;
            }
        }
        return true;
    }
}
