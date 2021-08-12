package com.yglong.leetcode.dailypractice._2021_08;

/**
 * 413. 等差数列划分
 * <p>
 * <p>
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * <p>
 * <p>
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * <p>
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * <p>
 * 子数组 是数组中的一个连续序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,2,3,4]
 * <p>
 * 输出：3
 * <p>
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1]
 * <p>
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 5000
 * <p>
 * -1000 <= nums[i] <= 1000
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices
 */
public class Day_2021_08_10 {
    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3}));
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5}));
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    /**
     * 双指针
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }

        int ans = 0;
        int i = 0;
        while (i < n - 2) {
            int j = i + 1;
            int sub = nums[j] - nums[i];
            while (j + 1 < n && nums[j + 1] - nums[j] == sub) {
                j++;
            }
            int len = j - i + 1;
            ans += (len - 2) * (len - 1) / 2;
            i = j;
        }
        return ans;
    }

    public static int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int d = nums[0] - nums[1], t = 0;
        int ans = 0;
        // 因为等差数列的长度至少为 3，所以可以从 i=2 开始枚举
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] - nums[i] == d) {
                ++t;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }
}
