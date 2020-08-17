package com.yglong.leetcode.array.remove;

/**
 * Remove Element
 *
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
public class RemoveElement {
    public static int removeElement(int[] a, int value) {
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != value) {
                a[j] = a[i];
                j++;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 2, 5};
        int len = removeElement(a, 2);

        for (int i = 0; i < len; i++) {
            System.out.println(a[i]);
        }
    }
}
