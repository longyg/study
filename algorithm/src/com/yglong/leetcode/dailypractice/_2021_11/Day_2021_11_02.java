package com.yglong.leetcode.dailypractice._2021_11;

/**
 * 237. 删除链表中的节点
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class Day_2021_11_02 {
    public static void main(String[] args) {
        ListNode l9 = new ListNode(9);
        ListNode l1 = new ListNode(1);
        l1.next = l9;
        ListNode l5 = new ListNode(5);
        l5.next = l1;
        ListNode l4 = new ListNode(4);
        l4.next = l5;

        deleteNode(l5);

        ListNode node = l4;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
