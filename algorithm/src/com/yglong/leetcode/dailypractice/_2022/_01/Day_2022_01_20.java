package com.yglong.leetcode.dailypractice._2022._01;

/**
 * 2029. 石子游戏 IX
 * <p>
 * <p>
 * 难度：中等
 * <p>
 * <p>
 * Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
 * <p>
 * Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones 中移除任一石子。
 * <p>
 * 如果玩家移除石子后，导致 所有已移除石子 的价值 总和 可以被 3 整除，那么该玩家就 输掉游戏 。
 * <p>
 * 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
 * <p>
 * 假设两位玩家均采用 最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/stone-game-ix
 * <p>
 * <p>
 * 输入：stones = [5,1,2,4,3]
 * <p>
 * 输出：false
 * <p>
 * 解释：Bob 总会获胜。其中一种可能的游戏进行方式如下：
 * <p>
 * - 回合 1：Alice 可以移除值为 1 的第 2 个石子。已移除石子值总和为 1 。
 * <p>
 * - 回合 2：Bob 可以移除值为 3 的第 5 个石子。已移除石子值总和为 = 1 + 3 = 4 。
 * <p>
 * - 回合 3：Alices 可以移除值为 4 的第 4 个石子。已移除石子值总和为 = 1 + 3 + 4 = 8 。
 * <p>
 * - 回合 4：Bob 可以移除值为 2 的第 3 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 = 10.
 * <p>
 * - 回合 5：Alice 可以移除值为 5 的第 1 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 + 5 = 15.
 * <p>
 * Alice 输掉游戏，因为已移除石子值总和（15）可以被 3 整除，Bob 获胜。
 */
public class Day_2022_01_20 {
    public boolean stoneGameIX(int[] stones) {
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        for (int val : stones) {
            int type = val % 3;
            if (type == 0) {
                ++cnt0;
            } else if (type == 1) {
                ++cnt1;
            } else {
                ++cnt2;
            }
        }
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }
        return cnt1 - cnt2 > 2 || cnt2 - cnt1 > 2;
    }
}
