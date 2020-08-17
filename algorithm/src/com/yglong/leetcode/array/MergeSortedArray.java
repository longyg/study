package com.yglong.leetcode.array;

import com.yglong.leetcode.array.utils.Utils;

import java.util.Arrays;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * Note: You may assume that A has enough space (size that is greater or equal to m + n) to hold
 * additional elements from B. The number of elements initialized in A and B are m and n respectively.
 *
 * 2, 3, 4, 6, 8
 * 2, 5, 7, 9, 10
 *
 */
public class MergeSortedArray {

    public static void merge(int[] a, int m, int[] b, int n) {
        int i = m - 1;
        int j = n - 1;
        for (int k = m + n - 1; k >= 0; k--) {
            if (b[j] > a[i]) {
                a[k] = b[j];
                j--;
            } else {
                a[k] = a[i];
                i--;
            }
            if (j < 0) {
                // 说明b数组第一个数比a数组头几个数大，因此先被遍历完且插入到了a数组中，此时a数组已经是合并且有序的了
                return;
            }
            if (i < 0) {
                // 说明a数组第一个数比b数组头几个数大，因此需要把b数组剩余的几个数插入到a数组前面
                for (; j >= 0; j--) {
                    a[j] = b[j];
                }
                return;
            }
        }
    }

    public static void main(String[] args) {
        int m = 6, n = 7;
        int[] a = Arrays.copyOf(new int[] {1, 3, 3, 4, 6, 8}, m + n);
        int[] b = new int[] {1, 2, 3, 5, 7, 9, 10};
        merge(a, m, b, n);
        System.out.println(Utils.join(a, ","));
    }
}
