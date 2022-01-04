package com.yglong.leetcode.dailypractice._2021._11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 559. N 叉树的最大深度
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 */
public class Day_2021_11_21 {
    public static void main(String[] args) {
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n2 = new Node(2);
        Node n4 = new Node(4);
        Node n3 = new Node(3, Arrays.asList(n5, n6));
        Node n1 = new Node(1, Arrays.asList(n3, n2, n4));
        System.out.println(new Day_2021_11_21().maxDepth(n1));
        System.out.println(new Day_2021_11_21().maxDepth(n5));
        System.out.println(new Day_2021_11_21().maxDepth(n3));
        System.out.println(new Day_2021_11_21().maxDepth(null));
    }

    /**
     * 广度搜索
     */
    public int maxDepth1(Node root) {
        if (root == null) return 0;
        int maxDepth = 1;
        List<Node> nodes = root.children;
        while (nodes != null && nodes.size() > 0) {
            maxDepth++;
            List<Node> children = new ArrayList<>();
            for (Node node : nodes) {
                if (node.children != null) {
                    children.addAll(node.children);
                }
            }
            nodes = children;
        }
        return maxDepth;
    }

    /**
     * 深度搜索
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        if (root.children == null) return 1;
        int maxDepth = 0;
        for (Node node : root.children) {
            maxDepth = Math.max(maxDepth, maxDepth(node));
        }
        return maxDepth + 1;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
