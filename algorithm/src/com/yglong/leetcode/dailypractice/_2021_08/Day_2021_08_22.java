package com.yglong.leetcode.dailypractice._2021_08;

/**
 * 789. 逃脱阻碍者
 * <p>
 * <p>
 * 你在进行一个简化版的吃豆人游戏。你从 [0, 0] 点开始出发，你的目的地是target = [xtarget, ytarget] 。
 * 地图上有一些阻碍者，以数组 ghosts 给出，第 i 个阻碍者从ghosts[i] = [xi, yi]出发。所有输入均为 整数坐标 。
 * <p>
 * <p>
 * 每一回合，你和阻碍者们可以同时向东，西，南，北四个方向移动，每次可以移动到距离原位置 1 个单位 的新位置。
 * 当然，也可以选择 不动 。所有动作 同时 发生。
 * <p>
 * <p>
 * 如果你可以在任何阻碍者抓住你 之前 到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。
 * 如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
 * <p>
 * <p>
 * 只有在你有可能成功逃脱时，输出 true ；否则，输出 false 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：ghosts = [[1,0],[0,3]], target = [0,1]
 * <p>
 * 输出：true
 * <p>
 * 解释：你可以直接一步到达目的地 (0,1) ，在 (1, 0) 或者 (0, 3) 位置的阻碍者都不可能抓住你。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：ghosts = [[1,0]], target = [2,0]
 * <p>
 * 输出：false
 * <p>
 * 解释：你需要走到位于 (2, 0) 的目的地，但是在 (1, 0) 的阻碍者位于你和目的地之间。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：ghosts = [[2,0]], target = [1,0]
 * <p>
 * 输出：false
 * <p>
 * 解释：阻碍者可以和你同时达到目的地。
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * <p>
 * 输入：ghosts = [[5,0],[-10,-2],[0,-5],[-2,-2],[-7,1]], target = [7,7]
 * <p>
 * 输出：false
 * <p>
 * <p>
 * 示例 5：
 * <p>
 * <p>
 * 输入：ghosts = [[-1,0],[0,1],[-1,0],[0,1],[-1,0]], target = [0,0]
 * <p>
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= ghosts.length <= 100
 * <p>
 * ghosts[i].length == 2
 * <p>
 * -104 <= xi, yi <= 104
 * <p>
 * 同一位置可能有 多个阻碍者 。
 * <p>
 * target.length == 2
 * <p>
 * -104 <= xtarget, ytarget <= 104
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/escape-the-ghosts
 */
public class Day_2021_08_22 {
    public static void main(String[] args) {
        System.out.println(escapeGhosts(new int[][]{{1, 0}, {0, 3}}, new int[]{0, 1}));
        System.out.println(escapeGhosts(new int[][]{{1, 0}}, new int[]{2, 0}));
    }


    public static boolean escapeGhosts(int[][] ghosts, int[] target) {
        // 自己到目标点的最短距离
        int d = distance(new int[]{0, 0}, target);
        // 计算每一个阻碍者到目标点的最短距离
        // 如果某个阻碍者到目标的距离比你到目标的距离短，则不能逃脱成功。
        for (int[] ghost : ghosts) {
            if (distance(ghost, target) <= d) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算两个点的距离：从一个点到另外一个点需要走的最短步数
     * 这种距离计算方式称为：哈曼顿距离
     */
    private static int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
