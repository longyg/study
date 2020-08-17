package com.yglong.leetcode.array.remove;

/**
 * Remove Duplicates from Sorted Array
 *
 * Given a sorted array, remove the duplicates in place such that each element appear only once
 * and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example, Given input array A = [1,1,2],
 * Your function should return length = 2, and A is now [1,2].
 */
public class RemoveAllDuplicate {

    public static int removeDuplicates(int[] a) {
        if (a.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 1; i < a.length; i++) {
            // if a[j] not equals to a[i], move j and set a[j] equals a[i]
            if (a[j] != a[i]) {
                a[++j] = a[i];
            }
        }
        return j + 1;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 2, 3, 3, 5};
        int len = removeDuplicates(a);
        for (int i = 0; i < len; i++) {
            System.out.println(a[i]);
        }
    }
}
