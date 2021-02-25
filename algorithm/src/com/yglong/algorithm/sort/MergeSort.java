package com.yglong.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序：
 * <p>
 * 分而治之的思想：
 * <p>
 * 1. 分：利先对序列拆分，一直拆分子序列只有两个数据，然后对这两个数据进行排序
 * <p>
 * 2. 治：对以排好序的子序列进行合并排序
 */
public class MergeSort {
    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    public static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);  // 对左边子序列排序
            sort(arr, mid + 1, right, temp);  // 对右边子序列排序
            merge(arr, left, mid, right, temp);  // 合并左右子序列
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        k = 0;
        while (left <= right) {
            arr[left++] = temp[k++];
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 0, 4, 6, 8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
