package com.yglong.leetcode.dailypractice._2021._12;

import java.util.Arrays;

/**
 * 825. 适龄的朋友
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * <p>
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * <p>
 * age[y] <= 0.5 * age[x] + 7
 * <p>
 * age[y] > age[x]
 * <p>
 * age[y] > 100 && age[x] < 100
 * <p>
 * 否则，x 将会向 y 发送一条好友请求。
 * <p>
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * <p>
 * 返回在该社交媒体网站上产生的好友请求总数。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages
 */
public class Day_2021_12_27 {
    public static void main(String[] args) {
        System.out.println(numFriendRequests(new int[]{58, 120, 117, 87, 19, 32, 91, 112, 106, 89, 5, 10, 65, 66, 106}));
        System.out.println(numFriendRequests(new int[]{16, 16}));
        System.out.println(numFriendRequests(new int[]{16, 17, 18}));
        System.out.println(numFriendRequests(new int[]{20, 30, 100, 110, 120}));
    }

    /**
     * 排序 + 二分查找
     */
    public static int numFriendRequests(int[] ages) {
        int n = ages.length;
        if (n < 2) return 0;
        Arrays.sort(ages);
        int total = 0;
        for (int i = n - 1; i > 0; i--) {
            int index = binarySearch(ages, (int) (0.5 * ages[i] + 7), i - 1);
            if (index == -1) continue;
            total += i - index;
            int j = i - 1;
            // 如果age相等，可以互相发送
            while (j >= index && ages[j] == ages[i]) {
                total++;
                j--;
            }
        }
        return total;
    }

    /**
     * 二分查找：从0到high之间，第一个大于age的元素位置
     */
    private static int binarySearch(int[] ages, int age, int high) {
        int low = 0;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (ages[mid] > age) {
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
