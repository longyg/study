package com.yglong.leetcode.array.remove;

/**
 * Remove Duplicates from Sorted Array II
 *
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
 * For example, Given sorted array A = [1,1,1,2,2,3],
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 *
 */
public class RemoveDuplicateMoreThan2 {
    public static int removeDuplicateMoreThanTwo(int[] a) {
        if (a.length == 0) {
            return 0;
        }
        int j = 0;
        int n = 0; // record duplicate times
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[j]) {
                n++;
            }
            // if duplicate is equals 2, reset n and move j
            // if a[i] not equals a[j], reset n and move j
            if (a[i] != a[j] || n == 2) {
                a[++j] = a[i];
                n = 0;
            }
        }
        return j+1;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 6, 7, 7};
        int len = removeDuplicateMoreThanTwo(a);

        for (int i = 0; i < len; i++) {
            System.out.println(a[i]);
        }
    }
}
