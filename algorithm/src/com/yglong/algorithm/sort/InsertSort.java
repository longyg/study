package com.yglong.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序：
 * 类似于玩扑克牌时，每次抽取一张牌，都插入到已经排好序的适当位置
 */
public class InsertSort {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int tmp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = tmp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {2, 3, 1, 0, 4, 6, 8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
