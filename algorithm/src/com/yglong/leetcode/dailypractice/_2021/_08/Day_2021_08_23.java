package com.yglong.leetcode.dailypractice._2021._08;

import java.util.Arrays;

/**
 * 1646. 获取生成数组中的最大值
 * <p>
 * <p>
 * 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
 * <p>
 * nums[0] = 0
 * <p>
 * nums[1] = 1
 * <p>
 * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
 * <p>
 * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
 * <p>
 * 返回生成数组 nums 中的 最大 值。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 7
 * <p>
 * 输出：3
 * <p>
 * 解释：根据规则：
 * <p>
 * nums[0] = 0
 * <p>
 * nums[1] = 1
 * <p>
 * nums[(1 * 2) = 2] = nums[1] = 1
 * <p>
 * nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 * <p>
 * nums[(2 * 2) = 4] = nums[2] = 1
 * <p>
 * nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 * <p>
 * nums[(3 * 2) = 6] = nums[3] = 2
 * <p>
 * nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * <p>
 * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 2
 * <p>
 * 输出：1
 * <p>
 * 解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：n = 3
 * <p>
 * 输出：2
 * <p>
 * 解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 0 <= n <= 100
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/get-maximum-in-generated-array
 */
public class Day_2021_08_23 {
    public static void main(String[] args) {
        System.out.println(getMaximumGenerated(7));
        System.out.println(getMaximumGenerated(2));
        System.out.println(getMaximumGenerated(3));
        System.out.println(getMaximumGenerated(1));
        System.out.println(getMaximumGenerated(0));
    }

    public static int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        int max = 1;
        for (int i = 2; i <= n; i++) {
            int k = i / 2;
            nums[i] = i % 2 == 0 ? nums[k] : nums[k] + nums[k+1];
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }
}
