package com.yglong.leetcode.dailypractice._2021_08;

import java.util.Arrays;

/**
 * 881. 救生艇
 * <p>
 * <p>
 * 第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。
 * <p>
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。
 * <p>
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：people = [1,2], limit = 3
 * <p>
 * 输出：1
 * <p>
 * 解释：1 艘船载 (1, 2)
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：people = [3,2,2,1], limit = 3
 * <p>
 * 输出：3
 * <p>
 * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：people = [3,5,3,4], limit = 5
 * <p>
 * 输出：4
 * <p>
 * 解释：4 艘船分别载 (3), (3), (4), (5)
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <=people.length <= 50000
 * <p>
 * 1 <= people[i] <=limit <= 30000
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/boats-to-save-people
 */
public class Day_2021_08_26 {
    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{3, 2, 2, 1}, 3));
        System.out.println(numRescueBoats(new int[]{1, 2}, 3));
        System.out.println(numRescueBoats(new int[]{3, 5, 3, 4}, 5));
        System.out.println(numRescueBoats(new int[]{3, 2, 3, 3, 2}, 6));
        System.out.println(numRescueBoats(new int[]{3, 8, 7, 1, 4}, 9));
    }

    /**
     * 排序 + 二分查找法
     */
    public static int numRescueBoats2(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);
        int cnt = 0;
        int index = n - 1;
        int i = 0;
        while (i <= index) {
            int k = binarySearch(people, i + 1, index, limit - people[i]);
            if (k != -1) { // 找到
                cnt += index - k + 1;
                index = k - 1;
            } else {
                // 没找到，则每个人都需要一艘船
                cnt += index - i + 1;
                break;
            }
            i++;
        }
        return cnt;
    }

    /**
     * 二分查找：找到倒数第一个等于或小于target的元素索引
     */
    private static int binarySearch(int[] people, int start, int end, int target) {
        int ret = -1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (people[mid] > target) {
                end = mid - 1;
            } else {
                ret = mid;
                start = mid + 1;
            }
        }
        return ret;
    }

    /**
     * 排序 + 贪心 + 双指针
     */
    public static int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);
        int cnt = 0;
        int i = 0, j = n - 1;
        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            cnt++;
            j--;
        }
        return cnt;
    }
}
