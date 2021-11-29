package com.yglong.leetcode.dailypractice._2021_11;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 786. 第 K 个最小的素数分数
 * <p>
 * <p>
 * 难度：困难
 * <p>
 * <p>
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
 * <p>
 * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
 * <p>
 * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
 * <p>
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,5], k = 3
 * <p>
 * 输出：[2,5]
 * <p>
 * 解释：已构造好的分数,排序后如下所示:
 * <p>
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 * <p>
 * 很明显第三个最小的分数是 2/5
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：arr = [1,7], k = 1
 * <p>
 * 输出：[1,7]
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= arr.length <= 1000
 * <p>
 * 1 <= arr[i] <= 3 * 104
 * <p>
 * arr[0] == 1
 * <p>
 * arr[i] 是一个 素数 ，i > 0
 * <p>
 * arr 中的所有数字 互不相同 ，且按 严格递增 排序
 * <p>
 * 1 <= k <= arr.length * (arr.length - 1) / 2
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/
 */
public class Day_2021_11_29 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 3)));
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 13, 17, 59}, 6)));
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 2029, 3209, 3517, 3823, 4933, 8209, 8893, 12763, 12799, 14197, 14387, 18973, 19207, 23747, 24547, 24953, 25247, 25763, 27043}, 1)));
    }

    /**
     * 暴力迭代 + 优先队列
     */
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 比较分数的大小，可以转化为比较乘法大小
        // a / b < c / d ===> a * d < b * c
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] * b[1] - a[1] * b[0]);
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                queue.offer(new int[]{arr[i], arr[j]});
            }
        }
        int[] ans = null;
        while (k > 0 && !queue.isEmpty()) {
            ans = queue.poll();
            k--;
        }
        return ans;
    }
}
