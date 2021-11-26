package com.yglong.leetcode.dailypractice._2021_11;

import java.util.Arrays;
import java.util.Random;

/**
 * 384. 打乱数组
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array/
 */
public class Day_2021_11_22 {

    class Solution {

        private int[] nums;
        private int[] originNums;
        private Random random = new Random();

        public Solution(int[] nums) {
            this.nums = nums;
            this.originNums = Arrays.copyOf(nums, nums.length);
        }

        public int[] reset() {
            this.nums = Arrays.copyOf(this.originNums, this.originNums.length);
            return this.nums;
        }

        public int[] shuffle() {
            for (int i = 0; i < nums.length; ++i) {
                int j = i + random.nextInt(nums.length - i);
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            return nums;
        }
    }
}
