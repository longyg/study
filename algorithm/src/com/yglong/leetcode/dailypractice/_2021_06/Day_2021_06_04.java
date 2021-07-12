package com.yglong.leetcode.dailypractice._2021_06;

import com.yglong.leetcode.dailypractice.entity.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * <p>
 * 输出：Intersected at '8'
 * <p>
 * <p>
 * 解释：
 * <p>
 * 相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * <p>
 * 提示：
 * <p>
 * listA 中节点数目为 m
 * <p>
 * listB 中节点数目为 n
 * <p>
 * 0 <= m, n <= 3 * 104
 * <p>
 * 1 <= Node.val <= 105
 * <p>
 * 0 <= skipA <= m
 * <p>
 * 0 <= skipB <= n
 * <p>
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * <p>
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 *
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 */
public class Day_2021_06_04 {
    private static ListNode getListA() {
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(8);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        return l1;
    }

    private static ListNode getListB() {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(6);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(8);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        return l1;
    }

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

        ListNode ret = getIntersectionNodeTwoPointer(a1, b1);
        System.out.println(ret == null ? "null" : ret.val);
    }

    /**
     * 哈希集合 HashSet
     */
    public static ListNode getIntersectionNodeHashMap(ListNode headA, ListNode headB) {
        Set<ListNode> listA = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            listA.add(temp);
            temp = temp.next;
        }

        temp = headB;
        while (temp != null) {
            if (listA.contains(temp)) {
                return temp; // 无需继续检查剩余节点，因为剩余节点是必然相同的
            }
            temp = temp.next;
        }

        return null;
    }

    /**
     * 双指针
     */
    public static ListNode getIntersectionNodeTwoPointer(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 暴力法
     */
    public static ListNode getIntersectionNodeExhaustively(ListNode headA, ListNode headB) {
        for (ListNode currentA = headA; currentA != null; currentA = currentA.next) {
            ListNode currentB = headB;
            while (currentB != null) {
                if (currentA != currentB) { // 注意，这里是判断地址，而不是判断节点值
                    currentB = currentB.next;
                } else {
                    return currentA;
                    // 无需继续检查剩余节点，因为剩余节点是必然相同的
//                    // 每当找到第一个相同的节点，然后判断剩余所有节点是否都相同
//                    if (isLeftSame(currentA, currentB)) {
//                        return currentA;
//                    } else {
//                        currentB = currentB.next;
//                    }
                }
            }
        }
        return null;
    }

    private static boolean isLeftSame(ListNode currentA, ListNode currentB) {
        ListNode tmpA = currentA;
        ListNode tmpB = currentB;
        while ((tmpA = tmpA.next) != null && (tmpB = tmpB.next) != null) {
            if (tmpA != tmpB) { // 注意，这里是判断地址，而不是判断节点值
                return false;
            }
        }
        return true;
    }
}


