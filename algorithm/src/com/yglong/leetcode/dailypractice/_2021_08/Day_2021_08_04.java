package com.yglong.leetcode.dailypractice._2021_08;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * <p>
 * <p>
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: [2,2,3,4]
 * <p>
 * 输出: 3
 * <p>
 * 解释:
 * <p>
 * 有效的组合是:
 * <p>
 * 2,3,4 (使用第一个 2)
 * <p>
 * 2,3,4 (使用第二个 2)
 * <p>
 * 2,2,3
 * <p>
 * <p>
 * 注意:
 * <p>
 * <p>
 * 数组长度不超过1000。
 * <p>
 * 数组里整数的范围为 [0, 1000]。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-triangle-number
 */
public class Day_2021_08_04 {
    public static void main(String[] args) {
        System.out.println(triangleNumber3(new int[]{2, 2, 3, 4}));
    }

    /**
     * 暴力迭代
     */
    public static int triangleNumber(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return 0;
        }
        Arrays.sort(nums);

        int ret = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int sum = nums[i] + nums[j];
                for (int k = j + 1; k < n; k++) {
                    if (sum <= nums[k]) {
                        break;
                    }
                    ret++;
                }

            }
        }
        return ret;
    }

    /**
     * 排序 + 二分查找
     */
    public static int triangleNumber2(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return 0;
        }
        Arrays.sort(nums);

        int ret = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int sum = nums[i] + nums[j];
                ret += binarySearch(nums, j + 1, sum) - j - 1;
            }
        }
        return ret;
    }

    /**
     * 二分查找，从start到最后的元素中，找到第一个大于target的元素位置
     */
    private static int binarySearch(int[] nums, int start, int target) {
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    /**
     * 排序 + 双指针
     */
    public static int triangleNumber3(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return 0;
        }
        Arrays.sort(nums);

        int ret = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1, k = j + 1; j < n - 1; j++) {
                while (k < n && nums[k] < nums[i] + nums[j]) {
                    k++;
                }
                ret += Math.max(k - (j + 1), 0);
            }
        }
        return ret;
    }

    public int triangleNumber5(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = 0; k < j; j--) {
                while (k < j && nums[k] + nums[j] <= nums[i]) k++;
                ans += j - k;
            }
        }
        return ans;
    }

}
