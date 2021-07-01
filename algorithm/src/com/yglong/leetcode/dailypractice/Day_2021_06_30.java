package com.yglong.leetcode.dailypractice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
        TreeNode right = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(5);
        root.right = left;
        root.right = right;

        right.left = left2;
        right.right = right2;

        String s = serialize(root);
        System.out.println(s);
        TreeNode n = deserialize(s);
        System.out.println(n);


    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    private static String rserialize(TreeNode root, String ret) {
        if (root == null) {
            ret += "null,";
        } else {
            ret += root.val + ",";
            ret = rserialize(root.left, ret);
            ret = rserialize(root.right, ret);
        }
        return ret;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] split = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(split));
        return rdeserialize(list);
    }

    private static TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("null")) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);

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
