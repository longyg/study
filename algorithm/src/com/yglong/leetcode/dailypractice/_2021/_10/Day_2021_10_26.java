package com.yglong.leetcode.dailypractice._2021._10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 496. 下一个更大元素 I
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i/
 */
public class Day_2021_10_26 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4})));
    }

    /**
     * 哈希表
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            indexMap.put(nums2[i], i);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = -1;
            int num = nums1[i];
            Integer index = indexMap.get(num);
            for (int j = index + 1; j < nums2.length; j++) {
                if (nums2[j] > num) {
                    ans[i] = nums2[j];
                    break;
                }
            }
        }
        return ans;
    }
}
