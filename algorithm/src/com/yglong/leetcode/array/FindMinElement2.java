package com.yglong.leetcode.array;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 2 4 5 6 7 might become 4 5 6 7 0 1 2 2).
 * Find the minimum element.
 * The array may contain duplicates.
 */
public class FindMinElement2 {

    /**
     * A[mid] > A[start]，右半区间查找。
     * A[mid] < A[start]，左半区间查找。
     * A[mid] = A[start]，出现这种情况，我们跳过start，重新查找，譬如[2,2,2,1]，A[mid] = A[start]都为2，这时候我们跳过start，使用[2,2,1]继续查找。
     * @param a
     * @return
     */
    public static int findMin(int[] a) {
        int len = a.length;
        if (len == 0) {
            return Integer.MIN_VALUE;
        } else if (len == 1) {
            return a[0];
        } else if (len == 2) {
            return Math.min(a[0], a[1]);
        }

        int start = 0;
        int end = a.length - 1;
        while (start < end - 1) {
            if (a[start] < a[end]) {
                return a[start];
            }

            int mid = start + (end - start) / 2;
            if (a[mid] > a[start]) {
                start = mid;
            } else if (a[mid] < a[start]) {
                end = mid;
            } else {
                start++;
            }
        }
        return Math.min(a[start], a[end]);
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[] {4, 5, 6, 6, 7, 0, 1, 2, 2}));
    }
}
