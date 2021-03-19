package com.yglong.algorithm.sort;

import com.yglong.algorithm.sort.util.Util;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {

    public static void sort(int[] arr) {
        for (int n = arr.length; n > 0; n--) {
            buildBigRootHeap(arr, n);
            // 构建大顶堆后，数组的第一个数就是最大，将最大数与数组最后元素交换
            Util.swap(arr, 0, n - 1);
        }
    }

    /**
     * 对数组的前n个数构建大顶堆
     */
    private static void buildBigRootHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, n);
        }
    }

    /**
     * 对节点i进行堆调整， 如果该节点还有子非叶节点，递归对子节点调整
     */
    private static void adjustHeap(int[] arr, int i, int n) {
        int left = 2 * i + 1;
        if (left >= n) {
            return;
        }
        int right = 2 * i + 2;

        int j = right < n ? Util.max(arr, left, right) : left;
        if (arr[i] < arr[j]) {
            Util.swap(arr, i, j);
            if (j <= n / 2 - 1) { // 如果j节点下面还有子节点，则重新调整j节点
                adjustHeap(arr, j, n);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 3, 10, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
