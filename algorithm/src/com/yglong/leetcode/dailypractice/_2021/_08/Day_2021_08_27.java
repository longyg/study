package com.yglong.leetcode.dailypractice._2021._08;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 295. 数据流的中位数
 * <p>
 * <p>
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4]的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * <p>
 * double findMedian() - 返回目前所有元素的中位数。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * addNum(1)
 * <p>
 * addNum(2)
 * <p>
 * findMedian() -> 1.5
 * <p>
 * addNum(3)
 * <p>
 * findMedian() -> 2
 * <p>
 * <p>
 * 进阶:
 * <p>
 * <p>
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * <p>
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 */
public class Day_2021_08_27 {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        int[] arr = new int[]{6, 10, 2, 6, 5, 0, 6, 3, 1, 0, 0};
        for (int a : arr) {
            medianFinder.addNum(a);
            System.out.println(medianFinder.findMedian());
        }
    }

    static class MedianFinder {

        // 两个优先级队列各保存一半元素
        // leftQueue的所有元素都比rightQueue的所有元素小
        private Queue<Integer> leftQueue, rightQueue;

        public MedianFinder() {
            // leftQueue为大顶堆，所以不能使用Integer的默认比较器
            leftQueue = new PriorityQueue<>(Collections.reverseOrder());
            rightQueue = new PriorityQueue<>();
        }

        // 如果两个队列元素个数相同，则添加元素到leftQueue，使其比rightQueue多一个元素
        // 如果leftQueue比rightQueue元素多1，则添加元素到rightQueue，使两个队列元素个数相同
        public void addNum(int num) {
            // 保证leftQueue优先插入
            if (leftQueue.isEmpty() || num <= leftQueue.peek()) {
                leftQueue.offer(num);
                if (rightQueue.size() + 1 < leftQueue.size()) {
                    rightQueue.offer(leftQueue.poll());
                }
            } else {
                rightQueue.offer(num);
                if (rightQueue.size() > leftQueue.size()) {
                    leftQueue.offer(rightQueue.poll());
                }
            }
        }

        public double findMedian() {
            if (leftQueue.size() > rightQueue.size()) {
                return leftQueue.peek();
            }
            return (leftQueue.peek() + rightQueue.peek()) / 2.0;
        }
    }
}
