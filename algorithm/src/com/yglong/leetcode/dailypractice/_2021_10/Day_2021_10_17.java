package com.yglong.leetcode.dailypractice._2021_10;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 230. 二叉搜索树中第K小的元素
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 */
public class Day_2021_10_17 {

    public static void main(String[] args) {
        Day_2021_10_17 t = new Day_2021_10_17();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2, t1, null);
        TreeNode t4 = new TreeNode(4);
        TreeNode t3 = new TreeNode(3, t2, t4);
        TreeNode t6 = new TreeNode(6);
        TreeNode t5 = new TreeNode(5, t3, t6);
        System.out.println(t.kthSmallest(t5, 3));
    }

    // 保存前k个最小的数，堆顶的最大
    Queue<Integer> lq = new PriorityQueue<>(Comparator.reverseOrder());
    int k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        // 堆顶元素就是第k小的元素
        return lq.peek();
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        // 当堆中元素个数小于k时，直接插入
        if (lq.size() < k) {
            lq.add(node.val);
        }
        // 如果新的元素比当前堆顶元素小，则用新元素交换堆顶元素
        else if (node.val < lq.peek()) {
            // 先弹出堆顶元素
            lq.poll();
            // 再插入新的元素
            lq.add(node.val);
        }
        dfs(node.left);
        dfs(node.right);
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
