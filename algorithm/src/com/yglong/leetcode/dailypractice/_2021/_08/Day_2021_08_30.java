package com.yglong.leetcode.dailypractice._2021._08;

import java.util.Random;

/**
 * 528. 按权重随机选择
 * <p>
 * <p>
 * 给定一个正整数数组w ，其中w[i]代表下标 i的权重（下标从 0 开始），请写一个函数pickIndex，
 * 它可以随机地获取下标 i，选取下标 i的概率与w[i]成正比。
 * <p>
 * <p>
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3)= 0.25 （即，25%），
 * 而选取下标 1 的概率为 3 / (1 + 3)= 0.75（即，75%）。
 * <p>
 * <p>
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：
 * <p>
 * ["Solution","pickIndex"]
 * <p>
 * [[[1]],[]]
 * <p>
 * 输出：
 * <p>
 * [null,0]
 * <p>
 * 解释：
 * <p>
 * Solution solution = new Solution([1]);
 * <p>
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：
 * <p>
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * <p>
 * [[[1,3]],[],[],[],[],[]]
 * <p>
 * 输出：
 * <p>
 * [null,1,1,1,1,0]
 * <p>
 * 解释：
 * <p>
 * Solution solution = new Solution([1, 3]);
 * <p>
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * <p>
 * solution.pickIndex(); // 返回 1
 * <p>
 * solution.pickIndex(); // 返回 1
 * <p>
 * solution.pickIndex(); // 返回 1
 * <p>
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 * <p>
 * <p>
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * <p>
 * [null,1,1,1,1,0]
 * <p>
 * [null,1,1,1,1,1]
 * <p>
 * [null,1,1,1,0,0]
 * <p>
 * [null,1,1,1,0,1]
 * <p>
 * [null,1,0,1,0,0]
 * <p>
 * ......
 * <p>
 * 诸若此类。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= w.length <= 10000
 * <p>
 * 1 <= w[i] <= 10^5
 * <p>
 * pickIndex将被调用不超过10000次
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/random-pick-with-weight
 */
public class Day_2021_08_30 {
    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 3});
        for (int i = 0; i < 10; i++) {
            System.out.println(solution.pickIndex());
        }
    }

    static class Solution {
        // 保存前缀和
        int[] prefixSum;
        Random random = new Random();

        public Solution(int[] w) {
            prefixSum = new int[w.length];
            prefixSum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + w[i];
            }
        }

        public int pickIndex() {
            // 生成1到所有数的总和之间的随机数
            int number = random.nextInt(prefixSum[prefixSum.length - 1]) + 1;
            // 因为前缀和数组是有序的，这里可以使用二分查找替代遍历
            return binarySearch(number);
        }

        /**
         * 二分查找
         */
        private int binarySearch(int target) {
            int low = 0;
            int high = prefixSum.length - 1;
            while (low < high) {
                int mid = (low + high) >> 1;
                if (prefixSum[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }
}
