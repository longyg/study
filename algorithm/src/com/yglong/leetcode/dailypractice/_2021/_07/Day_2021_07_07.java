package com.yglong.leetcode.dailypractice._2021._07;

import java.util.*;

/**
 * 1711. 大餐计数
 * <p>
 * <p>
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * <p>
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * <p>
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，
 * 返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 10^9 + 7 取余。
 * <p>
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：deliciousness = [1,3,5,7,9]
 * <p>
 * 输出：4
 * <p>
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * <p>
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= deliciousness.length <= 10^5
 * <p>
 * 0 <= deliciousness[i] <= 2^20
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/count-good-meals
 */
public class Day_2021_07_07 {

    public static void main(String[] args) {
        System.out.println(countPairs3(new int[]{149, 107, 1, 63, 0, 1, 1, 6867, 1325, 5611, 2581, 39, 89, 46, 18, 12, 20, 22, 234}));
    }

    /**
     * 暴力迭代法
     */
    public static int countPairs(int[] deliciousness) {
        final int MOD = 1000000007;
        Set<Integer> possibles = new HashSet<>();
        int n = deliciousness.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (deliciousness[i] > max) {
                max = deliciousness[i];
            }
        }
        int maxSum = max * 2;
        for (int sum = 1; sum <= maxSum; sum = sum << 1) {
            possibles.add(sum);
        }

        int ret = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int score = deliciousness[i] + deliciousness[j];
                if (possibles.contains(score)) {
                    ret++;
                }
            }
        }
        return ret % MOD;
    }

    /**
     * 排序+迭代
     */
    public static int countPairs2(int[] deliciousness) {
        final int MOD = 1000000007;
        int ret = 0;
        int n = deliciousness.length;
        Arrays.sort(deliciousness);
        Set<Integer> possibles = new HashSet<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (deliciousness[i] > max) {
                max = deliciousness[i];
            }
        }
        int maxSum = max * 2;
        for (int sum = 1; sum <= maxSum; sum = sum << 1) {
            possibles.add(sum);
        }

        int i = 0;
        while (i < n - 1) {
            int cur = deliciousness[i];
            int eq = 1;
            int j = i + 1;
            while (j < n && deliciousness[j] == cur) {
                j++;
                eq++;
                i++;
            }
            if (eq > 1) { // 如果有大于一个相同的
                int sameRet = 0;
                if (possibles.contains(cur * 2)) {
                    for (int k = eq - 1; k >= 1; k--) {
                        sameRet += k;
                    }
                }
                int subRet = 0;
                int k = i + 1;
                while (k < n) {
                    if (possibles.contains(cur + deliciousness[k])) {
                        subRet++;
                    }
                    k++;
                }
                ret += sameRet + eq * subRet;
            } else {
                int k = i + 1;
                while (k < n) {
                    if (possibles.contains(cur + deliciousness[k])) {
                        ret++;
                    }
                    k++;
                }
            }
            i++;
        }
        return ret % MOD;
    }

    /**
     * 官方解法
     */
    public static int countPairs3(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }
}
