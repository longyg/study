package com.yglong.leetcode.dailypractice._2021._08;

import java.util.ArrayList;
import java.util.List;

/**
 * 526. 优美的排列
 * <p>
 * <p>
 * 假设有从 1 到 N 的N个整数，如果从这N个数字中成功构造出一个数组，使得数组的第 i位 (1 <= i <= N) 满足如下两个条件中的一个，
 * 我们就称这个数组为一个优美的排列。条件：
 * <p>
 * 第i位的数字能被i整除
 * <p>
 * i 能被第 i 位上的数字整除
 * <p>
 * <p>
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * <p>
 * <p>
 * 示例1:
 * <p>
 * <p>
 * 输入: 2
 * <p>
 * 输出: 2
 * <p>
 * <p>
 * 解释:
 * <p>
 * <p>
 * 第 1 个优美的排列是 [1, 2]:
 * <p>
 * 第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 * <p>
 * 第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 * <p>
 * <p>
 * 第 2 个优美的排列是 [2, 1]:
 * <p>
 * 第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 * <p>
 * 第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 * <p>
 * <p>
 * 说明:
 * <p>
 * <p>
 * N 是一个正整数，并且不会超过15。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/beautiful-arrangement
 */
public class Day_2021_08_16 {

    public static void main(String[] args) {
        Day_2021_08_16 t = new Day_2021_08_16();
        System.out.println(t.countArrangement(1));
        System.out.println(t.countArrangement(2));
        System.out.println(t.countArrangement(3));
        System.out.println(t.countArrangement(4));
        System.out.println(t.countArrangement(15));
    }

    List<Integer>[] matches;
    boolean[] vis;
    int ans;

    /**
     * 回溯
     */
    public int countArrangement(int n) {
        // 每个位置满足条件的数存入matches数组中
        matches = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            matches[i] = list;
            for (int j = 1; j <= n; j++) {
                if (j % i == 0 || i % j == 0) {
                    list.add(j);
                }
            }
        }

        // 保存某个数是否已被使用
        vis = new boolean[n + 1];
        backtrack(1, n);
        return ans;
    }

    public void backtrack(int index, int n) {
        if (index == n + 1) { // 上一轮已经检查到第n个位置了，直接结束
            ans++;
            return;
        }

        for (Integer match : matches[index]) {
            if (!vis[match]) {
                vis[match] = true;
                backtrack(index + 1, n);
                vis[match] = false;
            }
        }
    }

    /**
     * 状态压缩 + 动态规划
     * <p>
     * <p>
     * 用 mask 的二进制表示选取状态，n 个数字用 n 位表示，第 i 位为 1 代表数字 i+1 已被选取（i从0开始），n 中 1 的个数 m 代表前 m 位已放置
     * <p>
     * <p>
     * 例如：二进制 100110 共三个1，代表排列的前三位已放置数字，三个1分别在二进制第 1、2、5位置上(从右侧开始，从0开始计数）,
     * <p>
     * 所以 2、3、6三个数字被选取，综合起来就是表示：2 3 6 这三个数字被放到了排列的前三位，三个数字完美排列方式未知，通过枚举 mask 进行计算
     */
    public int countArrangement2(int n) {
        // 用来存储中间结果，f[6] = f[000110] = 数字2、3在前两位时的完美排列数量
        int[] f = new int[1 << n];
        f[0] = 1;
        // 通过 mask 进行枚举，最终目的是为了得到二进制 mask = (11..11)n 时，总的完美排列数
        for (int mask = 1; mask < (1 << n); mask++) {
            int num = Integer.bitCount(mask);
            // 遍历 mask 的每一位，仍以 mask = 100110 为例，此 mask 代表 2 3 6三个数字在排列的前三位
            // 求三个数字 2 3 6 的完美排列方式，则先确定2 3 6哪些数字能放到第三位，然后累加另外两个数字的完美排列数量来获得
            // 2 3 6，第三位可以为 6，则 f[100110] += f[000110] (2、3在前两位时的完美排列数量)
            // 2 3 6，第三位可以为 3，则 f[100110] += f[100010] (2、6在前两位时的完美排列数量)
            for (int i = 0; i < n; i++) {
                // mask & (1<<i) 用来判断 mask 第 i 位是否为 1，如果为 1，说明第 i+1 个数字被选取
                // ((num % (i + 1)) == 0 || (i + 1) % num == 0) 判断被选取的数字 i+1 能否放到位置 num 上，
                // 即：先从被选取的数字中找到能放到最高位 num 的数字，然后将剩余 num-1 个数字的完美排列方式累加到f[mask]中
                if ((mask & (1 << i)) != 0 && ((num % (i + 1)) == 0 || (i + 1) % num == 0)) {
                    // mask ^ (1 << i) 将 mask 第 i 位设置为 0
                    f[mask] += f[mask ^ (1 << i)];
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
