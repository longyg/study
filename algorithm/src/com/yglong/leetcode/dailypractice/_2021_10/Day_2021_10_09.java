package com.yglong.leetcode.dailypractice._2021_10;

import java.util.*;

/**
 * 352. 将数据流变为多个不相交区间
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/
 */
public class Day_2021_10_09 {

    public static void main(String[] args) {
        SummaryRanges s = new SummaryRanges();
        s.addNum(1);
        System.out.println(Arrays.deepToString(s.getIntervals()));
        s.addNum(3);
        System.out.println(Arrays.deepToString(s.getIntervals()));
        s.addNum(7);
        System.out.println(Arrays.deepToString(s.getIntervals()));
        s.addNum(2);
        System.out.println(Arrays.deepToString(s.getIntervals()));
        s.addNum(6);
        System.out.println(Arrays.deepToString(s.getIntervals()));
    }


    static class SummaryRanges {

        TreeSet<Integer> nums = new TreeSet<>();

        public SummaryRanges() {

        }

        public void addNum(int val) {
            nums.add(val);
        }

        public int[][] getIntervals() {
            List<int[]> intervals = new ArrayList<>();

            if (nums.isEmpty()) return null;

            Iterator<Integer> iterator = nums.iterator();

            int l = iterator.next(); // 当前区间的左边界
            int r = l; // 当前区间的有边界
            while (iterator.hasNext()) {
                // 如果下一个数刚好与当前区间的右边界相邻，则增加右边界
                int next = iterator.next();
                if (next - r == 1) {
                    r = next;
                    continue;
                }
                // 否则，当前区间是一个独立的区间，添加进答案中
                intervals.add(new int[]{l, r});
                // 重置当前区间的左右边界为下一个数，继续查找下一个区间
                l = r = next;
            }
            intervals.add(new int[]{l, r});

            int[][] ans = new int[intervals.size()][2];
            for (int i = 0; i < intervals.size(); i++) {
                ans[i] = intervals.get(i);
            }
            return ans;
        }
    }

}
