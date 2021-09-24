package com.yglong.leetcode.dailypractice._2021_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 430. 扁平化多级双向链表
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/
 */
public class Day_2021_09_24 {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);

        node1.next = node2;
        node2.prev = node1;

        node2.next = node3;
        node3.prev = node2;

        node3.next = node4;
        node4.prev = node3;
        node3.child = node7;

        node4.next = node5;
        node5.prev = node4;

        node5.next = node6;
        node6.prev = node5;

        node7.next = node8;
        node8.prev = node7;

        node8.next = node9;
        node9.prev = node8;
        node8.child = node11;

        node9.next = node10;
        node10.prev = node9;

        node11.next = node12;
        node12.prev = node11;

        flatten(node1);
        List<Integer> l = new ArrayList<>();
        Node node = node1;
        while (node != null) {
            l.add(node.val);
            node = node.next;
        }
        System.out.println(Arrays.toString(l.toArray()));
    }

    /**
     * 深度优先搜索
     */
    public static Node flatten(Node head) {
        Node node = head;
        while (node != null) {
            if (node.child != null) {
                dfsChildList(node, node.child, node.next);
            }
            node = node.next;
        }
        return head;
    }

    private static void dfsChildList(Node parent, Node node, Node originNext) {
        // 把父子关系变为前后关系
        parent.next = node;
        node.prev = parent;
        // 将父节点的孩子置空
        parent.child = null;

        if (node.child != null) {
            dfsChildList(node, node.child, node.next);
        }

        // 遍历到该层链表的最后
        while (node.next != null) {
            node = node.next;
        }
        // 把子层最后的节点与上一层的next节点连接起来
        if (originNext != null) {
            originNext.prev = node;
            node.next = originNext;
        }
    }

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        Node(int val) {
            this.val = val;
        }
    }
}
