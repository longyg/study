package com.yglong.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序：
 * <p>
 * 对待排序列按照一定增量分组，分别对各个分组进行插入排序，最终当增量达到1时，进行最后一轮插入排序即可完成对整个数组的排序。
 * <p>
 * 增量逐渐缩小，最后为1。
 */
public class ShellSort {

    public static void sort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                    int temp = arr[j];
                    arr[j] = arr[j - gap];
                    arr[j - gap] = temp;
                    j -= gap;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 0, 4, 6, 8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
