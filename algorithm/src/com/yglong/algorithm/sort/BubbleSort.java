package com.yglong.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序：
 * 对相邻的两两数据进行比较，顺序相反则交换，这样，每一趟就会将最小或最大的元素“浮”到顶端，最终达到完全有序
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) { // 如果这一趟没有交换任何数据，说明剩下的待排序列已经有序，排序已经完成，则可以提前结束
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {2, 3, 1, 0, 4, 6, 8};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
}
