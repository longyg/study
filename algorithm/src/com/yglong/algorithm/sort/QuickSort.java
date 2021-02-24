package com.yglong.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序：
 *
 * 分而治之的思想
 *
 * 选择一个数作为基点，将小于这个数的放在一边，大于的数放在另外一边，然后递归的分别对左右两边的子序列使用同样的方式进行排序。
 * 直到所有子序列只有两个数时完成最后一趟排序结束
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        sort(arr, 0, 1, arr.length);
    }

    static void sort(int[] arr, int base, int start, int end) {
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[base]) {
                int j = i;
                int maxIndex = i;
                while (j > base) {
                    if (arr[j] > arr[maxIndex]) {
                        maxIndex = j;
                    }
                    j--;
                }
                if (maxIndex != i) {
                    int temp = arr[i];
                    arr[i] = arr[maxIndex];
                    arr[maxIndex] = temp;
                }
            }
        }
        int i = start;
        while (i < end) {
            if (arr[base] > arr[i]) {
                int temp = arr[i];
                arr[i] = arr[base];
                arr[base] = temp;
                base = i;
            } else {
                break;
            }
            i++;
        }

        if (base - start > 0) {
            sort(arr, start - 1, start, base);
        }
        if (end - base > 0) {
            sort(arr, base + 1, base + 2, end);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {2, 3, 1, 0, 4, 6, 8};
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }
}
