package com.yglong.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将二叉树转成数组，要求偶数层从右到左输出，奇数层从左到右输出
 */
public class Test3 {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2, n1, null);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4, n2, n5);
        TreeNode n3 = new TreeNode(3);
        TreeNode n9 = new TreeNode(9, n3, n4);
        TreeNode n22 = new TreeNode(2);
        TreeNode n10 = new TreeNode(10, n9, n22);
        System.out.println(Arrays.toString((new Test3().printTree(n10))));
    }

    public int[] printTree(TreeNode root) {
        if (root == null) return new int[0];
        int maxLayer = getMaxLayer(root);
        int maxLen = 0;
        for (int i = 0; i < maxLayer; i++) {
            maxLen += Math.pow(2, i);
        }
        List<Integer> ans = new ArrayList<>();
        TreeNode[] nodes = new TreeNode[maxLen];
        nodes[0] = root;
        ans.add(root.val);
        for (int i = 2; i <= maxLayer; i++) {
            // 偶数层，从右到左遍历
            if (i % 2 == 0) {
                for (int j = (int) Math.pow(2, i) - 1; j >= (int) Math.pow(2, i - 1); j -= 2) {
                    int index = j / 2 - 1;
                    TreeNode node = nodes[index];
                    if (node == null) continue;
                    // 先添加右边节点，再添加左边节点
                    if (node.right != null) {
                        ans.add(node.right.val);
                    }
                    if (node.left != null) {
                        ans.add(node.left.val);
                    }
                    nodes[(index + 1) * 2 - 1] = node.left;
                    nodes[(index + 1) * 2] = node.right;
                }
            } else {
                // 奇数层，从左到右遍历
                for (int j = (int) Math.pow(2, i - 1); j <= (int) Math.pow(2, i) - 1; j += 2) {
                    int index = j / 2 - 1;
                    TreeNode node = nodes[index];
                    if (node == null) continue;
                    // 先添加左边节点，再添加右边节点
                    if (node.left != null) {
                        ans.add(node.left.val);
                    }
                    if (node.right != null) {
                        ans.add(node.right.val);
                    }
                    nodes[(index + 1) * 2 - 1] = node.left;
                    nodes[(index + 1) * 2] = node.right;
                }
            }
        }
        int[] ret = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }

    private int getMaxLayer(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getMaxLayer(node.left), getMaxLayer(node.right)) + 1;
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
