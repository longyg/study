package com.yglong.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序：
 * <p>
 * 分而治之的思想
 * <p>
 * 选择一个数作为基点，将小于这个数的放在一边，大于的数放在另外一边，然后递归的分别对左右两边的子序列使用同样的方式进行排序。
 * 直到所有子序列只有两个数时完成最后一趟排序结束
 */
public class QuickSort {
    private static int swapCount = 0;

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int i = divide(arr, low, high); // 先拆分
            sort(arr, low, i - 1); // 递归对左边子序列排序
            sort(arr, i + 1, high); // 递归对右边子序列排序
        }
    }

    private static int divide(int[] arr, int low, int high) {
        int base = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && arr[j] > base) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            while (i < j && arr[i] < base) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = base;
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swapCount++;
    }

    public static void main(String[] args) {
        int[] a = new int[]{5, 0, 7, 6, 4, 3};
        sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println("交换次数：" + swapCount);
    }
}
