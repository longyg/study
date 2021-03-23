package com.yglong.datastructure.heap;

import com.yglong.algorithm.sort.util.Util;

import java.util.Arrays;

/**
 * 大顶堆
 * <p>
 * 用数组实现堆
 */
public class BigHeap {
    private int[] array;

    public BigHeap(int[] arr) {
        buildHeap(arr);
        this.array = arr;
    }

    private void buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i);
        }
    }

    private void adjustHeap(int[] arr, int i) {
        int left = 2 * i + 1;
        if (left >= arr.length) {
            return;
        }
        int right = 2 * i + 2;

        int j = right < arr.length ? Util.max(arr, left, right) : left;
        if (arr[i] < arr[j]) {
            Util.swap(arr, i, j);
            if (j <= arr.length / 2 - 1) {
                adjustHeap(arr, j);
            }
        }
    }

    public int[] getArray() {
        return this.array;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 3, 10, 7};
        BigHeap bigHeap = new BigHeap(arr);
        int[] array = bigHeap.getArray();
        System.out.println(Arrays.toString(array));
    }
}
