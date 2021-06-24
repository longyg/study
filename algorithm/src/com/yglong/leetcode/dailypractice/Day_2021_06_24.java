package com.yglong.leetcode.dailypractice;

/**
 * 149. 直线上最多的点数
 * <p>
 * <p>
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * <p>
 * <p>
 * 输入：points = [[1,1],[2,2],[3,3]]
 * <p>
 * 输出：3
 * <p>
 * <p>
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * <p>
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= points.length <= 300
 * <p>
 * points[i].length == 2
 * <p>
 * -104 <= xi, yi <= 104
 * <p>
 * points 中的所有点 互不相同
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/max-points-on-a-line
 */
public class Day_2021_06_24 {

    public static void main(String[] args) {
        System.out.println(maxPoints(new int[][]{{3, 3}, {1, 4}, {1, 1}, {2, 1}, {2, 2}}));
    }

    /**
     * 暴力法
     */
    public static int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int n = points.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int[] p1 = points[i];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int[] p2 = points[j];
                int a = p1[1] - p2[1];
                int b = p2[0] - p1[0];
                int c = p1[0] * p2[1] - p1[1] * p2[0];
                int curMax = 2;
                for (int k = j + 1; k < n; k++) { //只需判断比j大的点，因为比j小的点已经在上一轮比较过了
                    if (k == i || k == j) {
                        continue;
                    }
                    int[] p = points[k];
                    // 直线公式: A*x + B*y + C = 0
                    if (a * p[0] + b * p[1] + c == 0) {
                        curMax++;
                    }
                }
                if (curMax > max) {
                    max = curMax;
                }
            }

        }
        return max;
    }
}
