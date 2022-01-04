package com.yglong.leetcode.dailypractice._2021._07;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * <p>
 * <p>
 * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 K 。
 * <p>
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * <p>
 * 输出：[7,4,1]
 * <p>
 * 解释：
 * <p>
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * <p>
 * 值分别为 7，4，以及 1
 * <p>
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * <p>
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 给定的树是非空的。
 * <p>
 * 树上的每个结点都具有唯一的值0 <= node.val <= 500。
 * <p>
 * 目标结点target是树上的结点。
 * <p>
 * 0 <= K <= 1000.
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
 */
public class Day_2021_07_28 {
    public static void main(String[] args) {
        TreeNode l3 = new TreeNode(7, null, null);
        TreeNode r3 = new TreeNode(4, null, null);
        TreeNode l2_1 = new TreeNode(6, null, null);
        TreeNode r2_1 = new TreeNode(2, l3, r3);
        TreeNode l2_2 = new TreeNode(0, null, null);
        TreeNode r2_2 = new TreeNode(8, null, null);
        TreeNode l1 = new TreeNode(5, l2_1, r2_1);
        TreeNode r1 = new TreeNode(1, l2_2, r2_2);
        TreeNode root = new TreeNode(3, l1, r1);

        List<Integer> ret = new Day_2021_07_28().distanceK(root, l1, 2);
        System.out.println(Arrays.toString(ret.toArray()));
    }

    // 保存每个节点的父节点
    Map<TreeNode, TreeNode> parents = new HashMap<>();
    // 保存结果
    List<Integer> ret = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 使用深度优先（DFS）查找每个节点的父节点，并存入parents哈希表
        findParents(root);
        // 从target节点开始递归遍历树，同时反向递归父节点，找到深度为k的节点为止
        findResult(target, null, 0, k);
        return ret;
    }

    private void findParents(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left, node);
            findParents(node.left);
        }
        if (node.right != null) {
            parents.put(node.right, node);
            findParents(node.right);
        }
    }

    private void findResult(TreeNode node, TreeNode from, int depth, int k) {
        if (node == null) {
            return;
        }
        if (depth == k) {
            ret.add(node.val);
            return;
        }
        if (node.left != from) {
            findResult(node.left, node, depth + 1, k);
        }
        if (node.right != from) {
            findResult(node.right, node, depth + 1, k);
        }
        if (parents.get(node) != from) {
            findResult(parents.get(node), node, depth + 1, k);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }

        TreeNode(int x) {
            val = x;
        }
    }
}
