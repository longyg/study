package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 517. 超级洗衣机
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/super-washing-machines/
 */
public class Day_2021_09_29 {
    public static void main(String[] args) {
        System.out.println(findMinMoves(new int[]{0, 3, 0}));
    }

    /**
     * 贪心法
     */
    public static int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = 0;
        for (int i : machines) sum += i;
        if (sum % n != 0) return -1;
        int t = sum / n; // 最终每台的衣物数量
        int ls = 0, rs = sum; // 分别保存当前位置左右两边的衣物总量
        int ans = 0;
        for (int i = 0; i < n; i++) {
            rs -= machines[i]; // 右边衣物减少
            int a = Math.max(t * i - ls, 0); // 需要从右边往左边移动的衣物数量
            int b = Math.max((n - i - 1) * t - rs, 0); // 需要从左边往右边移动的衣物数量
            ans = Math.max(ans, a + b);
            ls += machines[i];
        }
        return ans;
    }
}
