package com.yglong.leetcode.dailypractice._2021._07;

import java.util.*;

/**
 * 1893. 检查是否区域内所有整数都被覆盖
 * <p>
 * <p>
 * 给你一个二维整数数组ranges和两个整数left和right。每个ranges[i] = [starti, endi]表示一个从starti到endi的闭区间。
 * <p>
 * 如果闭区间[left, right]内每个整数都被ranges中至少一个区间覆盖，那么请你返回true，否则返回false。
 * <p>
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi，那么我们称整数x被覆盖了。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * <p>
 * 输出：true
 * <p>
 * 解释：2 到 5 的每个整数都被覆盖了：
 * <p>
 * - 2 被第一个区间覆盖。
 * <p>
 * - 3 和 4 被第二个区间覆盖。
 * <p>
 * - 5 被第三个区间覆盖。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * <p>
 * 输出：false
 * <p>
 * 解释：21 没有被任何一个区间覆盖。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= ranges.length <= 50
 * <p>
 * 1 <= starti <= endi <= 50
 * <p>
 * 1 <= left <= right <= 50
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
 */
public class Day_2021_07_23 {
    public static void main(String[] args) {
        System.out.println(isCovered3(new int[][]{{1, 2}, {3, 4}, {5, 6}}, 2, 5));
        System.out.println(isCovered3(new int[][]{{1, 10}, {10, 20}}, 21, 21));
    }

    /**
     * 合并区域 + 判断
     */
    public static boolean isCovered(int[][] ranges, int left, int right) {
        int n = ranges.length;
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));

        // 先对所有区域进行合并
        List<int[]> mergedRange = new ArrayList<>();
        int l = ranges[0][0];
        int r = ranges[0][1];
        for (int i = 1; i < n; i++) {
            int[] range = ranges[i];
            if (range[0] > r + 1) {
                // 如果当前区域左边界比前一个区域的右边界还大的话，则无法合并，将其加入合并区域列表
                mergedRange.add(new int[]{l, r});
                l = range[0];
                r = range[1];
            } else {
                // 合并两个区域
                // 合并后的区域左边界不变，右边界取上一个区域与当前区域的最大值
                r = Math.max(r, range[1]);
            }
        }
        mergedRange.add(new int[]{l, r});

        // 再检查left到right是否在某一个合并后的区域里
        // 如果不在任何合并区域，则返回false
        for (int[] range : mergedRange) {
            if (range[0] <= left && range[1] >= right) {
                return true;
            }
        }
        return false;
    }


    /**
     * 哈希Set + 迭代
     */
    public static boolean isCovered2(int[][] ranges, int left, int right) {
        Set<Integer> set = new HashSet<>();
        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                set.add(i);
            }
        }

        for (int i = left; i <= right; i++) {
            if (!set.contains(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 差分
     */
    public static boolean isCovered3(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            ++diff[range[0]];
            --diff[range[1] + 1];
        }
        int cur = 0; // 前缀和
        for (int i = 1; i <= 50; i++) {
            cur += diff[i];
            if (i >= left && i <= right && cur <= 0) {
                return false;
            }
        }
        return true;
    }
}
