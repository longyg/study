package com.yglong.leetcode.dailypractice._2021_11;

import java.util.*;

/**
 * 391. 完美矩形
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/perfect-rectangle/
 */
public class Day_2021_11_16 {
    public static void main(String[] args) {
        System.out.println(isRectangleCover(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}}));
        System.out.println(isRectangleCover(new int[][]{{1, 1, 2, 3}, {1, 3, 2, 4}, {3, 1, 4, 2}, {3, 2, 4, 4}}));
        System.out.println(isRectangleCover(new int[][]{{0, 0, 4, 1}, {0, 0, 4, 1}}));
        System.out.println(isRectangleCover(new int[][]{{1, 2, 4, 4}, {1, 0, 4, 1}, {0, 2, 1, 3}, {0, 1, 3, 2}, {3, 1, 4, 2}, {0, 3, 1, 4}, {0, 0, 1, 1}}));
    }

    public static boolean isRectangleCover(int[][] rectangles) {
        // 保存所有竖边，并用标志为标识左竖边或右竖边
        Map<Integer, List<int[]>> m = new TreeMap<>(Comparator.comparingInt(a -> a));

        for (int[] rectangle : rectangles) {
            // 左边界
            List<int[]> lList = m.getOrDefault(rectangle[0], new ArrayList<>());
            lList.add(new int[]{rectangle[1], rectangle[3], 0});
            m.put(rectangle[0], lList);

            // 右边界
            List<int[]> rList = m.getOrDefault(rectangle[2], new ArrayList<>());
            rList.add(new int[]{rectangle[1], rectangle[3], 1});
            m.put(rectangle[2], rList);
        }


        List<int[]> l = new ArrayList<>();
        List<int[]> r = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Integer, List<int[]>> entry : m.entrySet()) {
            if (i == 0) {
                List<int[]> values = entry.getValue();
                for (int[] value : values) {
                    if (value[2] == 0) {
                        l.add(value);
                    }
                }
            } else if (i == m.size() - 1) {
                List<int[]> values = entry.getValue();
                for (int[] value : values) {
                    if (value[2] == 1) {
                        r.add(value);
                    }
                }
            } else {
                List<int[]> values = entry.getValue();
                values.sort(Comparator.comparingInt(a -> a[0]));
                int[] lLast = null;
                int[] rLast = null;
                int lSum = 0, rSum = 0;
                for (int[] value : values) {
                    if (value[2] == 0) { // 左边界
                        // 判断是否重叠
                        if (lLast != null) {
                            if (lLast[1] > value[0]) {
                                return false;
                            }
                        }
                        lSum += Math.abs(value[1] - value[0]);
                        lLast = value;
                    } else { // 有边界
                        // 判断是否重叠
                        if (rLast != null) {
                            if (rLast[1] > value[0]) {
                                return false;
                            }
                        }
                        rSum += Math.abs(value[1] - value[0]);
                        rLast = value;
                    }
                }
                if (lSum != rSum) return false;
            }
            i++;
        }

        int lSum = 0, rSum = 0;
        int[] lLast = null;
        l.sort(Comparator.comparingInt(a -> a[0]));
        for (int[] ints : l) {
            // 判断是否重叠
            if (lLast != null) {
                if (lLast[1] > ints[0]) {
                    return false;
                }
            }
            lSum += Math.abs(ints[1] - ints[0]);
            lLast = ints;
        }
        int[] rLast = null;
        r.sort(Comparator.comparingInt(a -> a[0]));
        for (int[] ints : r) {
            // 判断是否重叠
            if (rLast != null) {
                if (rLast[1] > ints[0]) {
                    return false;
                }
            }
            rSum += Math.abs(ints[1] - ints[0]);
            rLast = ints;
        }
        return lSum == rSum;
    }
}
