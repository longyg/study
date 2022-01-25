package com.yglong.leetcode.dailypractice._2022._01;

/**
 * 1688. 比赛中的配对次数
 * <p>
 * <p>
 * 难度：简单
 * <p>
 * <p>
 * 给你一个整数 n ，表示比赛中的队伍数。比赛遵循一种独特的赛制：
 * <p>
 * 如果当前队伍数是 偶数 ，那么每支队伍都会与另一支队伍配对。总共进行 n / 2 场比赛，且产生 n / 2 支队伍进入下一轮。
 * <p>
 * 如果当前队伍数为 奇数 ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 (n - 1) / 2 场比赛，且产生 (n - 1) / 2 + 1 支队伍进入下一轮。
 * <p>
 * 返回在比赛中进行的配对次数，直到决出获胜队伍为止。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/count-of-matches-in-tournament
 */
public class Day_2022_01_25 {
    public static void main(String[] args) {
        System.out.println(numberOfMatches(10));
        System.out.println(numberOfMatches(5));
        System.out.println(numberOfMatches(7));
        System.out.println(numberOfMatches(14));
    }

    public static int numberOfMatches(int n) {
        int ans = 0;
        while (n > 1) {
            int t = n / 2;
            ans += t;
            n = t + n % 2;
        }
        return ans;
    }
}
