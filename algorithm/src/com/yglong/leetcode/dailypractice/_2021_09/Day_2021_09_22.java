package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 725. 分隔链表
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts/
 */
public class Day_2021_09_22 {
    public static void main(String[] args) {
        ListNode l10 = new ListNode(10, null);
        ListNode l9 = new ListNode(9, l10);
        ListNode l8 = new ListNode(8, l9);
        ListNode l7 = new ListNode(7, l8);
        ListNode l6 = new ListNode(6, l7);
        ListNode l5 = new ListNode(5, l6);
        ListNode l4 = new ListNode(4, l5);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);
        ListNode[] ans = splitListToParts(l1, 3);
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];
        int size = 0;
        ListNode node = head;
        // 获取链表总长度
        while (node != null) {
            size++;
            node = node.next;
        }
        // 如果链表长度小于k，则链表的每个元素都应该被分为单独的一组
        // 且最后不足的部分补null
        if (size <= k) {
            node = head;
            for (int i = 0; i < k; i++) {
                if (node == null) {
                    ans[i] = null;
                } else {
                    ans[i] = node;
                    node = node.next;
                    ans[i].next = null;
                }
            }
        } else {
            // 如果链表长度大于k，则链表的元素足够分成k部分，无需补null
            int n = size;
            int part = k;
            node = head;
            for (int i = 0; i < k; i++) {
                ans[i] = node;
                int s = n / part;
                // 如果剩下的元素不能恰好分为part组，则该组应该多分一个元素
                if (n % part != 0) {
                    s++;
                }
                ListNode last = ans[i];
                int l = s;
                // 连续走过s个元素，构成该组的元素集合
                while (l > 0) {
                    last = node;
                    node = node.next;
                    l--;
                }
                // 该组最后一个元素的next应该断开
                last.next = null;
                part--;
                n = n - s;
            }
        }
        return ans;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
