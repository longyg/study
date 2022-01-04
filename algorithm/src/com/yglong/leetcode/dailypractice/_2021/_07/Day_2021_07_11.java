package com.yglong.leetcode.dailypractice._2021._07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 274. H 指数
 * <p>
 * <p>
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h指数。
 * <p>
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * 且其余的N - h篇论文每篇被引用次数不超过 h 次。
 * <p>
 * <p>
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入：citations = [3,0,6,1,5]
 * <p>
 * 输出：3
 * <p>
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * <p>
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * <p>
 * <p>
 * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/h-index
 */
public class Day_2021_07_11 {
    public static void main(String[] args) {
        System.out.println(hIndex2(new int[]{3, 0, 6, 1, 5}));
        System.out.println(hIndex2(new int[]{0, 1, 0, 1, 3, 2, 2, 4, 2, 4}));
        System.out.println(hIndex2(new int[]{1, 3, 1}));
        System.out.println(hIndex2(new int[]{1, 2, 2}));
    }

    /**
     * 哈希表
     */
    public static int hIndex(int[] citations) {
        int n = citations.length;
        if (n == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int h = 0;
        for (int i = 0; i < n; i++) {
            int cur = citations[i];
            if (cur >= h) {
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
            if (cur >= h + 1 && getSum(map, h + 1) >= h + 1) {
                h++;
            }
        }
        return h;
    }

    private static int getSum(Map<Integer, Integer> map, int h) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (num >= h) {
                sum += count;
            }
        }
        return sum;
    }

    /**
     * 滑动窗口
     */
    public static int hIndex2(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int h = 0;
        int low = 0;
        int high = 0;
        while (low <= high && high < n) {
            if (citations[low] > h) {
                h = high - low + 1; // h 等于当前窗口大小
            }
            if (h == 0 || citations[low] < h || high - low >= h) {
                low++; // 移动窗口的左边
            }
            high++; // 移动窗口的右边
        }
        return h;
    }
}
