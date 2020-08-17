package com.yglong.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
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

    public static void sort3(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int idx = i;
            // 找到比idx小的最小那一个
            for (int j = i + 1; j < a.length; j++) {
                if (a[idx] > a[j]) {
                    idx = j;
                }
            }
            if (idx != i) {
                int tmp = a[i];
                a[i] = a[idx];
                a[idx] = tmp;
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