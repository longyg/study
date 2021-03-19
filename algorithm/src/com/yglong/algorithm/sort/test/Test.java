package com.yglong.algorithm.sort.test;

import com.yglong.algorithm.sort.*;
import com.yglong.algorithm.sort.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws Exception {
        // 冒泡排序
        test(BubbleSort.class);

        // 堆排序
        test(HeapSort.class);

        // 插入排序
        test(InsertSort.class);

        // 归并排序
        test(MergeSort.class);

        // 快速排序
        test(QuickSort.class);

        // 双路快速排序
        test(DualQuickSort.class);

        // 选择排序
        test(SelectSort.class);

        // 希尔排序
        test(ShellSort.class);
    }

    private static void test(Class clazz) throws Exception {
        int[] arr = getRandomArray(10);
        Method sortMethod = clazz.getDeclaredMethod("sort", int[].class);
        StopWatch stopWatch = new StopWatch();
        sortMethod.invoke(null, arr);
        System.out.println(clazz.getSimpleName() + ": " + Arrays.toString(arr) + ", time: " + stopWatch.end());
    }

    public static int[] getRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(50);
        }
        return arr;
    }
}
