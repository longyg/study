package com.yglong.leetcode.dailypractice._2022._01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2013. 检测正方形
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：
 * <p>
 * 添加 一个在数据流中的新点到某个数据结构中。可以添加 重复 的点，并会视作不同的点进行处理。
 * <p>
 * 给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 面积为正 的 轴对齐正方形 ，统计 满足该要求的方案数目。
 * <p>
 * 轴对齐正方形 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。
 * <p>
 * <p>
 * 实现 DetectSquares 类：
 * <p>
 * DetectSquares() 使用空数据结构初始化对象
 * <p>
 * void add(int[] point) 向数据结构添加一个新的点 point = [x, y]
 * <p>
 * int count(int[] point) 统计按上述方式与点 point = [x, y] 共同构造 轴对齐正方形 的方案数。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/detect-squares
 */
public class Day_2022_01_26 {

    public static void main(String[] args) {
        DetectSquares ds = new DetectSquares();
        ds.add(new int[]{5, 10});
        ds.add(new int[]{10, 5});
        ds.add(new int[]{10, 10});
        System.out.println(ds.count(new int[]{5, 5}));
        ds.add(new int[]{3, 0});
        ds.add(new int[]{8, 0});
        ds.add(new int[]{8, 5});
        System.out.println(ds.count(new int[]{3, 5}));
        ds.add(new int[]{9, 0});
        ds.add(new int[]{9, 8});
        ds.add(new int[]{1, 8});
        System.out.println(ds.count(new int[]{1, 0}));
        ds.add(new int[]{0, 0});
        ds.add(new int[]{8, 0});
        ds.add(new int[]{8, 8});
        System.out.println(ds.count(new int[]{0, 8}));
    }

    static class DetectSquares {

        private Map<Integer, List<int[]>> xMap = new HashMap<>();
        private Map<Integer, List<int[]>> yMap = new HashMap<>();
        private Map<Integer, Map<Integer, List<int[]>>> xyMap = new HashMap<>();

        public DetectSquares() {

        }

        public void add(int[] point) {
            List<int[]> xlist = xMap.getOrDefault(point[0], new ArrayList<>());
            xlist.add(point);
            xMap.put(point[0], xlist);
            List<int[]> ylist = yMap.getOrDefault(point[1], new ArrayList<>());
            ylist.add(point);
            yMap.put(point[1], ylist);
            Map<Integer, List<int[]>> ym = xyMap.getOrDefault(point[0], new HashMap<>());
            List<int[]> list = ym.getOrDefault(point[1], new ArrayList<>());
            list.add(point);
            ym.put(point[1], list);
            xyMap.put(point[0], ym);
        }

        public int count(int[] point) {
            int x = point[0], y = point[1];
            List<int[]> xlist = xMap.get(x);
            if (null == xlist || xlist.size() < 1) return 0;
            int ans = 0;
            for (int[] p1 : xlist) {
                if (p1[1] == y) continue;
                List<int[]> ylist = yMap.get(p1[1]);
                if (null == ylist || ylist.size() < 1) continue;
                int len = Math.abs(p1[1] - y); // 正方形的边长
                for (int[] p2 : ylist) {
                    if (p2[0] == x || Math.abs(p2[0] - x) != len) continue;
                    if (xyMap.containsKey(p2[0])) {
                        Map<Integer, List<int[]>> ym = xyMap.get(p2[0]);
                        if (ym.containsKey(y)) {
                            ans += ym.get(y).size();
                        }
                    }
                }
            }
            return ans;
        }
    }
}
