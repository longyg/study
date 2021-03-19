package com.yglong.algorithm.sort.util;

public class Util {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int max(int[] arr, int i, int j) {
        if (arr[i] < arr[j]) {
            return j;
        } else {
            return i;
        }
    }
}
