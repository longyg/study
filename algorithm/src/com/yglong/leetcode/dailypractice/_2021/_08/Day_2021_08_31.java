package com.yglong.leetcode.dailypractice._2021._08;

import java.util.*;

/**
 * 1109. 航班预订统计
 * <p>
 * <p>
 * 这里有n个航班，它们分别从 1 到 n 进行编号。
 * <p>
 * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
 * <p>
 * 请你返回一个长度为 n 的数组answer，其中 answer[i] 是航班 i 上预订的座位总数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * <p>
 * 输出：[10,55,45,25,25]
 * <p>
 * 解释：
 * <p>
 * 航班编号        1   2   3   4   5
 * <p>
 * 预订记录 1 ：   10  10
 * <p>
 * 预订记录 2 ：       20  20
 * <p>
 * 预订记录 3 ：       25  25  25  25
 * <p>
 * 总座位数：      10  55  45  25  25
 * <p>
 * 因此，answer = [10,55,45,25,25]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * <p>
 * 输出：[10,25]
 * <p>
 * 解释：
 * <p>
 * 航班编号        1   2
 * <p>
 * 预订记录 1 ：   10  10
 * <p>
 * 预订记录 2 ：       15
 * <p>
 * 总座位数：      10  25
 * <p>
 * 因此，answer = [10,25]
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= n <= 2 * 104
 * <p>
 * 1 <= bookings.length <= 2 * 104
 * <p>
 * bookings[i].length == 3
 * <p>
 * 1 <= firsti <= lasti <= n
 * <p>
 * 1 <= seatsi <= 104
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 */
public class Day_2021_08_31 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5)));
        System.out.println(Arrays.toString(corpFlightBookings(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2)));
    }

    /**
     * 差分数组
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] d = new int[n];
        for (int[] booking : bookings) {
            d[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                d[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            d[i] += d[i - 1];
        }
        return d;
    }

    /**
     * 哈希表
     */
    public static int[] corpFlightBookings1(int[][] bookings, int n) {
        Map<Integer, Integer> seatMap = new HashMap<>();
        for (int[] booking : bookings) {
            for (int i = booking[0]; i <= booking[1]; i++) {
                Integer seats = seatMap.getOrDefault(i, 0);
                seats += booking[2];
                seatMap.put(i, seats);
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = seatMap.get(i + 1) == null ? 0 : seatMap.get(i + 1);
        }
        return ans;
    }

    public static int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int[] booking : bookings) {
                if (booking[0] <= i + 1 && booking[1] >= i + 1) {
                    ans[i] += booking[2];
                }
            }
        }
        return ans;
    }
}
