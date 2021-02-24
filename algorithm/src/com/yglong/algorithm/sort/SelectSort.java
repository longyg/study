package com.yglong.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序：
 * 每一趟从待排序数据元素中选择最大（或最小）的一个元素，直到所有元素排完为止
 */
public class SelectSort {
    // 最暴力的排序方法，每一趟比较所有数
    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] < a[j]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    // 每一趟只比较无序部分
    public static void sort2(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    // 避免无用的交换
    public static void sort3(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i; // 存储每一趟比较后最小的那个数的位置
            // 找到比idx小的最小那一个
            for (int j = i + 1; j < a.length; j++) {
                if (a[minIndex] > a[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) { // 如果最小位置不是当前位置，则交换
                int tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {2, 3, 1, 0, 4, 6, 8};
        sort(a);
        System.out.println(Arrays.toString(a));

        int[] b = new int[] {2, 3, 10, 0, 4, 6, 8};
        sort2(b);
        System.out.println(Arrays.toString(b));

        int[] c = new int[] {2, 3, 10, 1, 4, 6, 8};
        sort3(c);
        System.out.println(Arrays.toString(c));
    }
}