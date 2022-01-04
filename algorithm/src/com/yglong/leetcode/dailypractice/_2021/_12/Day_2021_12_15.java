package com.yglong.leetcode.dailypractice._2021._12;

import java.util.*;

/**
 * 851. 喧闹和富有
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号为 x 的人简称为 "person x "。
 * <p>
 * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自恰（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
 * <p>
 * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/loud-and-rich
 */
public class Day_2021_12_15 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(loudAndRich(
                new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}},
                new int[]{3, 2, 5, 4, 6, 1, 7, 0})));
        System.out.println(Arrays.toString(loudAndRich(new int[][]{}, new int[]{0})));
    }

    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[][] q = new int[n][2];
        for (int i = 0; i < n; i++) {
            q[i] = new int[]{i, quiet[i]};
        }
        Arrays.sort(q, Comparator.comparingInt(a -> a[1]));

        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int[] r : richer) {
            Set<Integer> set = m.getOrDefault(r[1], new HashSet<>());
            set.add(r[0]);
            m.put(r[1], set);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (!m.containsKey(i)) {
                ans[i] = i;
                continue;
            }
            Set<Integer> all = new HashSet<>();
            all.add(i);
            boolean[] accessed = new boolean[n];
            collect(all, m, i, accessed);

            for (int[] k : q) {
                if (all.contains(k[0])) {
                    ans[i] = k[0];
                    break;
                }
            }
        }
        return ans;
    }

    private static void collect(Set<Integer> all, Map<Integer, Set<Integer>> m, int i, boolean[] accessed) {
        if (accessed[i]) {
            return;
        }
        all.addAll(m.get(i));
        accessed[i] = true;
        for (Integer index : m.get(i)) {
            if (m.containsKey(index)) {
                collect(all, m, index, accessed);
            }
        }
    }
}
