package com.yglong.leetcode.dailypractice._2021._11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 519. 随机翻转矩阵
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/random-flip-matrix/
 */
public class Day_2021_11_27 {

    public static void main(String[] args) {
        Solution s = new Solution(3, 1);
        System.out.println(Arrays.toString(s.flip()));
        System.out.println(Arrays.toString(s.flip()));
        System.out.println(Arrays.toString(s.flip()));
        s.reset();
        System.out.println(Arrays.toString(s.flip()));
    }


    private static class Solution {
        int m, n, total;
        Map<Integer, Integer> map = new HashMap<>();
        Random random = new Random();

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            this.total = m * n;
        }

        public int[] flip() {
            int x = random.nextInt(total);
            total--;
            int idx = map.getOrDefault(x, x);
            map.put(x, map.getOrDefault(total, total));
            return new int[] {idx / n, idx % n};
        }

        public void reset() {
            total = m * n;
            map.clear();
        }
    }
}
