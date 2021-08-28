package com.yglong.leetcode.dailypractice._2021_08;

import java.util.Arrays;

/**
 * 1480. 一维数组的动态和
 * <p>
 * <p>
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 * <p>
 * 请返回 nums 的动态和。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,2,3,4]
 * <p>
 * 输出：[1,3,6,10]
 * <p>
 * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1,1,1,1,1]
 * <p>
 * 输出：[1,2,3,4,5]
 * <p>
 * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [3,1,2,10,1]
 * <p>
 * 输出：[3,4,6,16,17]
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 1000
 * <p>
 * -10^6<= nums[i] <=10^6
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array
 */
public class Day_2021_08_28 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(runningSum(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(runningSum(new int[]{1, 1, 1, 1, 1})));
        System.out.println(Arrays.toString(runningSum(new int[]{3, 1, 2, 10, 1})));
    }

    public static int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}
