package com.yglong.leetcode.dailypractice._2021_10;

import java.util.ArrayList;
import java.util.List;

/**
 * 335. 路径交叉
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/self-crossing/
 */
public class Day_2021_10_29 {
    public static void main(String[] args) {
        System.out.println(isSelfCrossing(new int[]{2, 1, 1, 2}));
        System.out.println(isSelfCrossing(new int[]{1, 2, 3, 4}));
        System.out.println(isSelfCrossing(new int[]{1, 1, 1, 1}));
        System.out.println(isSelfCrossing(new int[]{1, 1, 2, 1, 1}));
    }

    public static boolean isSelfCrossing(int[] distance) {
        int n = distance.length;

        // 处理第 1 种情况
        int i = 0;
        while (i < n && (i < 2 || distance[i] > distance[i - 2])) {
            ++i;
        }

        if (i == n) {
            return false;
        }

        // 处理第 j 次移动的情况
        if ((i == 3 && distance[i] == distance[i - 2])
                || (i >= 4 && distance[i] >= distance[i - 2] - distance[i - 4])) {
            distance[i - 1] -= distance[i - 3];
        }
        ++i;

        // 处理第 2 种情况
        while (i < n && distance[i] < distance[i - 2]) {
            ++i;
        }

        return i != n;
    }

    public static boolean isSelfCrossing1(int[] d) {
        int n = d.length;
        if (n < 4) return false;
        for (int i = 3; i < n; i++) {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) return true;
            if (i >= 4 && d[i - 1] == d[i - 3]
                    && d[i] + d[i - 4] >= d[i - 2]) return true;
            if (i >= 5 && d[i - 1] <= d[i - 3]
                    && d[i - 2] > d[i - 4]
                    && d[i] + d[i - 4] >= d[i - 2]
                    && d[i - 1] + d[i - 5] >= d[i - 3])
                return true;
        }
        return false;
    }


    public static boolean isSelfCrossing2(int[] distance) {
        List<Point> points = new ArrayList<>();
        int direction = 1; // 1, 2, 3, 4 分别代表北，西，南，东4个方向
        Point lastPoint = new Point(0, 0);
        points.add(lastPoint);
        for (int d : distance) {
            Point p = new Point();
            if (direction == 1) {
                p.x = lastPoint.x;
                p.y = lastPoint.y + d;
            } else if (direction == 2) {
                p.x = lastPoint.x - d;
                p.y = lastPoint.y;
            } else if (direction == 3) {
                p.x = lastPoint.x;
                p.y = lastPoint.y - d;
            } else if (direction == 4) {
                p.x = lastPoint.x + d;
                p.y = lastPoint.y;
            }
            points.add(p);
            if (direction == 4) {
                direction = 0;
            }
            direction++;
            lastPoint = p;
        }

        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i);
            Point b = points.get(i + 1);
            for (int j = i + 3; j < points.size() - 1; j++) {
                Point c = points.get(j);
                Point d = points.get(j + 1);
                if (intersection(a, b, c, d)) {
                    return true;
                }
            }

        }
        return false;
    }

    private static boolean intersection(Point a, Point b, Point c, Point d) {
        if (!(Math.min(a.x, b.x) <= Math.max(c.x, d.x)
                && Math.min(c.y, d.y) <= Math.max(a.y, b.y)
                && Math.min(c.x, d.x) <= Math.max(a.x, b.x)
                && Math.min(a.y, b.y) <= Math.max(c.y, d.y)))
            return false;
        int u = (c.x - a.x) * (b.y - a.y) - (b.x - a.x) * (c.y - a.y);
        int v = (d.x - a.x) * (b.y - a.y) - (b.x - a.x) * (d.y - a.y);
        int w = (a.x - c.x) * (d.y - c.y) - (d.x - c.x) * (a.y - c.y);
        int z = (b.x - c.x) * (d.y - c.y) - (d.x - c.x) * (b.y - c.y);
        return (u * v <= 0.00000001 && w * z <= 0.00000001);
    }

    static class Point {
        int x, y;

        Point() {
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
