package com.yglong.leetcode.dailypractice._2021_07;

/**
 * 275. H 指数 II
 * <p>
 * <p>
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照升序排列。编写一个方法，计算出研究者的 h 指数。
 * <p>
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * （其余的N - h篇论文每篇被引用次数不多于 h 次。）"
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/h-index-ii
 */
public class Day_2021_07_12 {
    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{1, 1, 1, 1, 3, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10}));
    }

    /**
     * 二分查找
     */
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int low = 0;
        int high = n - 1;
        int h = 0; // h指数
        while (low <= high) {
            int mid = low + (high - low) / 2; // 将数组分成两部分
            int rightLen = high - mid + 1; // 右半部分元素个数
            if (citations[mid] == rightLen + h) {
                h += rightLen;
                return h;
            }
            if (citations[mid] > rightLen + h) {
                h += rightLen;
                high = mid - 1;
            } else if (citations[mid] < rightLen + h) {
                low = mid + 1;
            }
        }
        return h;
    }
}
