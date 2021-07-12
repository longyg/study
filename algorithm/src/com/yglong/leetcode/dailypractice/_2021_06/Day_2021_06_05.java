package com.yglong.leetcode.dailypractice._2021_06;

import com.yglong.leetcode.dailypractice.entity.ListNode;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * <p>
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * <p>
 * 输出：[1,2,3,4,5]
 * <p>
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * <p>
 * 输出：[]
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class Day_2021_06_05 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(6);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(5);
        ListNode l7 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        System.out.println(removeElements(l1, 6));
    }

    /**
     * 迭代法
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        while (head != null) {
            if (head.val == val) {
                head = head.next;
            } else {
                break;
            }
        }
        if (head == null) {
            return null;
        }
        ListNode tmp = head;
        while (tmp.next != null) {
            if (tmp.next.val == val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return head;
    }

    /**
     * 递归法
     */
    public static ListNode removeElementsRecurse(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElementsRecurse(head.next, val);
        return head.val == val ? head.next : head;
    }
}
