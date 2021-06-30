package com.yglong.leetcode.dailypractice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 37. 序列化二叉树
 * <p>
 * <p>
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * <p>
 * 输出：[1,2,3,null,null,4,5]
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
 */
public class Day_2021_06_30 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
//        TreeNode right = new TreeNode(3);
//        TreeNode left2 = new TreeNode(4);
//        TreeNode right2 = new TreeNode(5);
        root.right = left;
//        root.right = right;
//
//        right.left = left2;
//        right.right = right2;

        String s = serialize(root);
        System.out.println(s);
        TreeNode n = deserialize(s);
        System.out.println(n);
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null) {
                    sb.append(queue.size() > 0 ? ",null" : "");
                } else {
                    sb.append(",").append(node.left.val);
                    queue.offer(node.left);
                }

                if (node.right == null) {
                    sb.append(queue.size() > 0 ? ",null" : "");
                } else {
                    sb.append(",").append(node.right.val);
                    queue.offer(node.right);
                }
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] strs = data.split(",");
        if (strs.length == 1) {
            return new TreeNode(Integer.parseInt(data.trim()));
        }

        TreeNode root = new TreeNode(Integer.parseInt(strs[0].trim()));

        int h = (int) Math.sqrt(strs.length + 1);
        TreeNode[] nodes = new TreeNode[strs.length];
        nodes[0] = root;
        for (int i = 0; i <= h; i++) {
            int l = 2 * i + 1;
            if (l >= strs.length) {
                break;
            }
            TreeNode node = nodes[i];
            if (node == null) continue;
            String left = strs[l].trim();
            String right = strs[l + 1].trim();
            if (!left.equals("null")) {
                node.left = new TreeNode(Integer.parseInt(left));
                nodes[l] = node.left;
            }
            if (!right.equals("null")) {
                node.right = new TreeNode(Integer.parseInt(right));
                nodes[l + 1] = node.right;
            }
        }

        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
