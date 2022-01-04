package com.yglong.leetcode.dailypractice._2021._09;

/**
 * 223. 矩形面积
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/rectangle-area/
 */
public class Day_2021_09_30 {
    public static void main(String[] args) {
        System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
        System.out.println(computeArea(-2, -2, 2, 2, -2, -2, 2, 2));
        System.out.println(computeArea(-2, -2, 2, 2, -1, -1, 1, 1));
    }

    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 计算两个矩形的总面积
        int total = getArea(ax1, ay1, ax2, ay2) + getArea(bx1, by1, bx2, by2);
        // 计算重叠X
        int ox = getOverlap(ax1, ax2, bx1, bx2);
        // 计算重叠Y
        int oy = getOverlap(ay1, ay2, by1, by2);
        // 最终结果等于总面积减去重叠面积
        return total - ox * oy;
    }

    private static int getArea(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (y2 - y1);
    }

    private static int getOverlap(int a1, int a2, int b1, int b2) {
        if (a1 <= b1 && b2 <= a2) {
            return b2 - b1;
        }
        if (b1 <= a1 && a2 <= b2) {
            return a2 - a1;
        }
        if (a1 <= b2 && a1 >= b1) {
            return b2 - a1;
        }
        if (b1 <= a2 && b1 >= a1) {
            return a2 - b1;
        }
        return 0;
    }
}
