package com.yglong.leetcode.dailypractice._2021_10;

/**
 * 240. 搜索二维矩阵 II
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 */
public class Day_2021_10_25 {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}}, 20));

        System.out.println(searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}}, 5));

        System.out.println(searchMatrix(new int[][]{{-5}}, -5));
        System.out.println(searchMatrix(new int[][]{{5}, {6}}, 6));
    }

    /**
     * 对每行进行二分查找
     * <p>
     * 时间复杂度O(m*logn)
     */
    public static boolean searchMatrix1(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (binarySearch(row, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean binarySearch(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] < target) {
                low = mid + 1;
            } else if (row[mid] > target) {
                high = mid - 1;
            }
        }
        return false;
    }

    /**
     * 优化的二分查找方法，减少了二分查找的范围
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int index = -1;
        while (i < m) {
            if (index != -1 && matrix[i][index] == target) return true;
            int low = index == -1 ? 0 : (matrix[i][index] > target ? 0 : index);
            int high = index == -1 ? n - 1 : (matrix[i][index] > target ? index : n - 1);
            index = binarySearch2(matrix[i], low, high, target);
            if (index == -1) break;
            if (matrix[i][index] == target) return true;
            i++;
        }
        return false;
    }

    /**
     * 二分查找，找到最后一个不大于target的索引位置
     */
    private static int binarySearch2(int[] row, int low, int high, int target) {
        int ret = -1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (row[mid] <= target) {
                ret = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ret;
    }

}
