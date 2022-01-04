package com.yglong.leetcode.dailypractice._2021._07;

import com.yglong.leetcode.dailypractice.entity.ListNode;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * <p>
 * 本题与 2021-6-4的题相同： https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * <p>
 * <p>
 * 输入两个链表，找出它们的第一个公共节点。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * <p>
 * 输出：Reference of the node with value = 8
 * <p>
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 */
public class Day_2021_07_21 {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(1);

        ListNode c1 = new ListNode(8);
        ListNode c2 = new ListNode(4);
        ListNode c3 = new ListNode(5);

        a1.next = a2;
        a2.next = c1;

        b1.next = b2;
        b2.next = b3;
        b3.next = c1;

        c1.next = c2;
        c3.next = c3;

        ListNode ret = getIntersectionNode(a1, b1);
        System.out.println(ret == null ? "null" : ret.val);
    }

    /**
     * 两个指针分别从两条链表头同步前进，如果找到相同的则返回。
     * <p>
     * 如果任意一个指针走到链表的最后一个节点，就折返到另外一条链表开头继续前进，
     * 最终两个指针指向相同的节点，则找到了相交节点，如果没有，则说明没有相交节点，返回null
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) { // a和b相等，或者a和b都为null，结束循环
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
