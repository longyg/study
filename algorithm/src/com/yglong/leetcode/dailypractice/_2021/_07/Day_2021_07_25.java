package com.yglong.leetcode.dailypractice._2021._07;

import java.util.*;

/**
 * 1743. 从相邻元素对还原数组
 * <p>
 * <p>
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 * <p>
 * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
 * <p>
 * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。
 * <p>
 * 这些相邻元素对可以 按任意顺序 出现。
 * <p>
 * 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：adjacentPairs = [[2,1],[3,4],[3,2]]
 * <p>
 * 输出：[1,2,3,4]
 * <p>
 * 解释：数组的所有相邻元素对都在 adjacentPairs 中。
 * <p>
 * 特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * <p>
 * 输出：[-2,4,1,-3]
 * <p>
 * 解释：数组中可能存在负数。
 * <p>
 * 另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：adjacentPairs = [[100000,-100000]]
 * <p>
 * 输出：[100000,-100000]
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * nums.length == n
 * <p>
 * adjacentPairs.length == n - 1
 * <p>
 * adjacentPairs[i].length == 2
 * <p>
 * 2 <= n <= 105
 * <p>
 * -105 <= nums[i], ui, vi <= 105
 * <p>
 * 题目数据保证存在一些以adjacentPairs 作为元素对的数组 nums
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs
 */
public class Day_2021_07_25 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(restoreArray(new int[][]{{2, 1}, {3, 4}, {3, 2}})));
        System.out.println(Arrays.toString(restoreArray(new int[][]{{4, -2}, {1, 4}, {-3, 1}})));
        System.out.println(Arrays.toString(restoreArray(new int[][]{{100000, -100000}})));
    }

    public static int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> m = new HashMap<>();

        // 遍历数组，找到每个数的所有相邻数
        // 如示例1中:
        // 2 -> [1,3], 2与1和3相邻
        // 1 -> [2]， 1与2相邻
        // 3 -> [4,2]， 3与4和2相邻
        // 4 -> [3]， 4与3相邻
        for (int[] adjacentPair : adjacentPairs) {
            m.putIfAbsent(adjacentPair[0], new ArrayList<>());
            m.get(adjacentPair[0]).add(adjacentPair[1]);
            m.putIfAbsent(adjacentPair[1], new ArrayList<>());
            m.get(adjacentPair[1]).add(adjacentPair[0]);
        }

        int[] arr = new int[adjacentPairs.length + 1];

        // 数组第一个应该从只有一个相邻数的数开始
        // 查找map，找到第一个只有一个相邻数的数作为第一个元素
        for (Map.Entry<Integer, List<Integer>> entry : m.entrySet()) {
            if (entry.getValue().size() == 1) {
                arr[0] = entry.getKey();
                break;
            }
        }

        arr[1] = m.get(arr[0]).get(0); // 第一个元素的相邻元素只有一个

        // 循环
        // 查找上一个元素的所有相邻数，如果有多个相邻数（最多2个），则将还未加入数组的那一个加入数组当前位置
        for (int i = 2; i <= adjacentPairs.length; i++) {
            List<Integer> v = m.get(arr[i - 1]);
            arr[i] = arr[i - 2] == v.get(0) ? v.get(1) : v.get(0);
        }

        return arr;
    }
}
