package com.yglong.leetcode.dailypractice._2021_11;

/**
 * 700. 二叉搜索树中的搜索
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 */
public class Day_2021_11_26 {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2, n1, n3);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4, n2, n7);
        TreeNode node = new Day_2021_11_26().searchBST(n4, 2);
        System.out.println(node.val);
        node = new Day_2021_11_26().searchBST(n4, 5);
        System.out.println(node);
    }

    /**
     * 递归法
     * <p>
     * 空间复杂度为O(n)，最坏情况下需要递归n次，所以需要O(n)的栈空间
     */
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        return searchBST1(root.val > val ? root.left : root.right, val);
    }

    /**
     * 迭代法
     * <p>
     * 空间复杂度O(1)
     */
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) return root;
            root = root.val > val ? root.left : root.right;
        }
        return null;
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
