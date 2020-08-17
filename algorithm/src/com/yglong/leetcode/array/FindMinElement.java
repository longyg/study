package com.yglong.leetcode.array;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 7 0 1 2 4 5 6
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 */
public class FindMinElement {

    /**
     * O(n)解法
     * @param a
     * @return
     */
    public static int findMin(int[] a) {
        if (a.length < 2) {
            return a[0];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length - 1; i++) {
            if (i - 1 >= 0 && a[i - 1] > a[i] && a[i] < a[i + 1]) {
                min = a[i];
                return min;
            } else if (a[i + 1] > a[i]) {
                if (a[i] < min) {
                    min = a[i];
                }
            } else if (a[i + 1] < a[i]) {
                if (a[i + 1] < min) {
                    min = a[i + 1];
                }
            }

        }

        return min;
    }

    /**
     * 二分法
     *
     * 假设在一个轮转的排序数组A，我们首先获取中间元素的值，A[mid]，mid = (start + stop) / 2。因为数组没
     * 有重复元素，那么就有两种情况：
     * A[mid] > A[start]，那么最小值一定在右半区间，譬如[4,5,6,7,0,1,2]，中间元素为7，7 > 4，最小元素
     * 一定在[7,0,1,2]这边，于是我们继续在这个区间查找。
     * A[mid] < A[start]，那么最小值一定在左半区间，譬如[7,0,1,2,4,5,6]，这件元素为2，2 < 7，我们继续
     * 在[7,0,1,2]这个区间查找。
     */
    public static int findMin2(int[] a) {
        if (a.length < 1) {
            return 0;
        } else if (a.length < 2) {
            return a[0];
        } else if (a.length == 2) {
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
            }
        }
        return Math.min(a[start], a[end]);
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[] {4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin2(new int[] {4, 5, 6, 7, 1, 2}));
    }
}
