package com.yglong.leetcode.dailypractice._2021_10;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 284. 窥探迭代器
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/peeking-iterator/
 */
public class Day_2021_10_05 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        PeekingIterator peekingIterator = new PeekingIterator(Arrays.stream(arr).iterator());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.peek());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.hasNext());
    }


    static class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer next;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            this.next = this.iterator.next(); // 初始化为第一个元素
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return this.next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer item = this.next;
            if (this.iterator.hasNext()) {
                // 移动到下一个
                this.next = this.iterator.next();
            } else {
                this.next = null;
            }
            return item;
        }

        @Override
        public boolean hasNext() {
            return this.next != null;
        }
    }
}
