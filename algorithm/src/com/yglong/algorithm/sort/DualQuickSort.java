package com.yglong.algorithm.sort;

import com.yglong.algorithm.sort.util.Util;

import java.util.Arrays;
import java.util.Random;

public class DualQuickSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int i = split(arr, low, high);
            sort(arr, low, i - 1);
            sort(arr, i + 1, high);
        }
    }

    private static int split(int[] arr, int low, int high) {
        Random random = new Random();
        int base = random.nextInt(high-low + 1) + low;
        Util.swap(arr, low, base);

        int key = arr[low];
        int i = low + 1;
        int j = high;
        while(true) {
            while (i <= high && arr[i] < key) {
                i++;
            }
            while (j >= low && arr[j] > key) {
                j--;
            }
            if (i > j) {
                break;
            }
            Util.swap(arr, i, j);
            i++;
            j--;
        }
        Util.swap(arr, low, j);
        return j;
    }

    public static void main(String[] args) {
        int[] a = new int[]{5, 0, 7, 6, 4, 3};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
