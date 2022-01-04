package com.yglong.leetcode.dailypractice._2021._09;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 */
public class Day_2021_09_02 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(4);
        ListNode last = new ListNode(5);
        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = last;

        System.out.println(getKthFromEnd3(head, 2).val); // 4
    }

    /**
     * 时间复杂度O(n)
     * <p>
     * 空间复杂度O(n)
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        // 用一个ArrayList保存所有节点
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        // 返回ArrayList中第size-k个元素，即链表的倒数第k个节点
        return list.get(list.size() - k);
    }


    /**
     * 时间复杂度2 * O(n)，因为要遍历两次链表
     * <p>
     * 空间复杂度O(1)
     */
    public static ListNode getKthFromEnd2(ListNode head, int k) {
        int n = 0;
        ListNode node;
        // 遍历链表得到链表长度
        for (node = head; node != null; node = node.next) {
            n++;
        }
        // 再次遍历链表返回倒数第k个节点
        for (node = head; n > k; n--) {
            node = node.next;
        }
        return node;
    }

    /**
     * 双指针法（快慢指针）
     * <p>
     * 时间复杂度O(n)
     * <p>
     * 空间复杂度O(1)
     */
    public static ListNode getKthFromEnd3(ListNode head, int k) {
        ListNode fast = head, slow = head;
        // fast指针先走k步
        while (k-- > 0) fast = fast.next;
        // fast和slow指针同步走，直到fast指针走到最后，slow指针指向的节点就是倒数第k个节点
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
