package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 437. 路径总和 III
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/path-sum-iii/
 */
public class Day_2021_09_28 {
    public static void main(String[] args) {
        TreeNode n9 = new TreeNode(1);
        TreeNode n8 = new TreeNode(-2);
        TreeNode n7 = new TreeNode(3);
        TreeNode n6 = new TreeNode(11);
        TreeNode n5 = new TreeNode(2, null, n9);
        TreeNode n4 = new TreeNode(3, n7, n8);
        TreeNode n3 = new TreeNode(-3, null, n6);
        TreeNode n2 = new TreeNode(5, n4, n5);
        TreeNode n1 = new TreeNode(10, n2, n3);
        System.out.println(new Day_2021_09_28().pathSum(n1, 8));
    }

    private int ans = 0;
    private int targetSum;

    /**
     * 深度优先搜索
     * <p>
     * 以每一个节点为起始节点向下搜索，只要遇到结果值，则累加
     */
    public int pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        startDfs(root);
        return ans;
    }

    private void startDfs(TreeNode start) {
        if (start == null) return;
        dfs(start, this.targetSum);
        startDfs(start.left);
        startDfs(start.right);
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) return;
        sum -= node.val;
        if (sum == 0) {
            ans++;
        }
        dfs(node.left, sum);
        dfs(node.right, sum);
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
