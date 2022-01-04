package com.yglong.leetcode.dailypractice._2021._08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 313. 超级丑数
 * <p>
 * <p>
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * <p>
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * <p>
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 12, primes = [2,7,13,19]
 * <p>
 * 输出：32
 * <p>
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 1, primes = [2,3,5]
 * <p>
 * 输出：1
 * <p>
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= n <= 106
 * <p>
 * 1 <= primes.length <= 100
 * <p>
 * 2 <= primes[i] <= 1000
 * <p>
 * 题目数据 保证 primes[i] 是一个质数
 * <p>
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/super-ugly-number
 */
public class Day_2021_08_09 {

    public static void main(String[] args) {
        System.out.println(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
    }

    /**
     * 本题的关键是要搞明白什么是丑数，什么是超级丑数，以及它们有什么特性和规律
     * <p>
     * 质数：一个数只能被1和它本身整除，这个数就是质数
     * <p>
     * 质因数：一个数的因数是质数，这个质数就叫做这个数的质因数
     */
    public static int nthSuperUglyNumber2(int n, int[] primes) {
        HashSet<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;

        for (int i = 0; i < n; i++) {
            long cur = heap.poll();
            ugly = (int) cur;
            for (int prime : primes) {
                long next = cur * prime;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }

        return ugly;
    }

    /**
     * 动态规划
     */
    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        int m = primes.length;
        int[] pointers = new int[m];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; i++) {
            int[] nums = new int[m];
            int minNum = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                nums[j] = dp[pointers[j]] * primes[j];
                minNum = Math.min(minNum, nums[j]);
            }
            dp[i] = minNum;
            for (int j = 0; j < m; j++) {
                if (minNum == nums[j]) {
                    pointers[j]++;
                }
            }
        }
        return dp[n];
    }
}
