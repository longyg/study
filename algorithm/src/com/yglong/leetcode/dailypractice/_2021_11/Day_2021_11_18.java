package com.yglong.leetcode.dailypractice._2021_11;

/**
 * 563. 二叉树的坡度
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-tree-tilt/
 */
public class Day_2021_11_18 {
    public static void main(String[] args) {
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n1 = new TreeNode(1, n2, n3);
        System.out.println(new Day_2021_11_18().findTilt(n1));


    }

    private int sum = 0;
    public int findTilt(TreeNode root) {
        dfs(root);
        return sum;
    }

    /**
     * 深度搜索，计算坡度的同时计算子树和
     */
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int leftSum = dfs(node.left);
        int rightSum = dfs(node.right);
        // 累加当前节点的坡度
        sum += Math.abs(leftSum - rightSum);
        // 返回当前节点所在的子树和
        return node.val + leftSum + rightSum;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
