package com.yglong.leetcode.dailypractice._2021_07;

import java.util.Arrays;

/**
 * 1838. 最高频元素的频数
 * <p>
 * <p>
 * 元素的 频数 是该元素在一个数组中出现的次数。
 * <p>
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 * <p>
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,4], k = 5
 * <p>
 * 输出：3
 * <p>
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * <p>
 * 4 是数组中最高频元素，频数是 3 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,4,8,13], k = 5
 * <p>
 * 输出：2
 * <p>
 * 解释：存在多种最优解决方案：
 * <p>
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * <p>
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * <p>
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [3,9,6], k = 2
 * <p>
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * <p>
 * 1 <= nums[i] <= 105
 * <p>
 * 1 <= k <= 105
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element
 */
public class Day_2021_07_19 {
    public static void main(String[] args) {
        System.out.println(maxFrequency(new int[]{1, 2, 4}, 5));
        System.out.println(maxFrequency(new int[]{1, 4, 8, 13}, 5));
        System.out.println(maxFrequency(new int[]{3, 9, 6}, 2));
        System.out.println(maxFrequency(new int[]{9930, 9923, 9983, 9997, 9934, 9952, 9945, 9914, 9985, 9982,
                        9970, 9932, 9985, 9902, 9975, 9990, 9922, 9990, 9994, 9937, 9996, 9964, 9943, 9963,
                        9911, 9925, 9935, 9945, 9933, 9916, 9930, 9938, 10000, 9916, 9911, 9959, 9957, 9907,
                        9913, 9916, 9993, 9930, 9975, 9924, 9988, 9923, 9910, 9925, 9977, 9981, 9927, 9930,
                        9927, 9925, 9923, 9904, 9928, 9928, 9986, 9903, 9985, 9954, 9938, 9911, 9952, 9974,
                        9926, 9920, 9972, 9983, 9973, 9917, 9995, 9973, 9977, 9947, 9936, 9975, 9954, 9932,
                        9964, 9972, 9935, 9946, 9966},
                3056));
        System.out.println(maxFrequency(new int[]{0, 1, 2, 2, 4}, 2));
    }

    /**
     * 排序 + 迭代
     * 时间复杂度为排序的时间复杂度：O(nlogn)
     */
    public static int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums); // 对数组排序
        int freq = 1; // 保存当前频数，当i=0时，freq为1
        int prevSum = nums[0]; // 保存freq个元素总和，初始为数组第一个元素值
        int i = 1;
        // 迭代数组
        while (i < n) {
            int cur = nums[i];
            /*
            求出最大需要多少次操作，最大需要的操作次数needK为：当前元素分别减去前freq个元素的差的总和。
            needK = (cur - nums[i - 1]) + (cur - nums[i-2]) + ... + (cur - nums[i-freq])
                  = cur * freq - (nums[i - 1] + ... nums[i - freq])
                  = cur * freq - prevSum
            prevSum 保存了前freq个元素总和
             */
            int needK = cur * freq - prevSum;
            prevSum += nums[i];
            if (needK <= k) {
                // 当允许的总操作次数k充足时，freq增加1
                freq++;
            } else {
                // 当允许的总操作次数k不足时，prevSum剔除掉前freq个元素中的第一个，即i-freq
                prevSum -= nums[i - freq];
            }
            i++;
        }
        return freq;
    }
}
