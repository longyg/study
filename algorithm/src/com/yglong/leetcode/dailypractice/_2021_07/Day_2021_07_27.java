package com.yglong.leetcode.dailypractice._2021_07;

/**
 * 671. 二叉树中第二小的节点
 * <p>
 * <p>
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为2或0。
 * <p>
 * 如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * <p>
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 * <p>
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [2,2,5,null,null,5,7]
 * <p>
 * 输出：5
 * <p>
 * 解释：最小的值是 2 ，第二小的值是 5 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [2,2,2]
 * <p>
 * 输出：-1
 * <p>
 * 解释：最小的值是 2, 但是不存在第二小的值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 树中节点数目在范围 [1, 25] 内
 * <p>
 * 1 <= Node.val <= 231 - 1
 * <p>
 * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree
 */
public class Day_2021_07_27 {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(2, null, null);
        TreeNode l2 = new TreeNode(5, null, null);
        TreeNode r2 = new TreeNode(7, null, null);
        TreeNode r1 = new TreeNode(5, l2, r2);
        TreeNode root = new TreeNode(2, l1, r1);
        System.out.println(findSecondMinimumValue(root));

        l1 = new TreeNode(2, null, null);
        r1 = new TreeNode(2, null, null);
        root = new TreeNode(2, l1, r1);
        System.out.println(findSecondMinimumValue(root));

        TreeNode l5_1 = new TreeNode(2, null, null);
        TreeNode r5_1 = new TreeNode(3, null, null);
        TreeNode l5_2 = new TreeNode(2, null, null);
        TreeNode r5_2 = new TreeNode(4, null, null);
        TreeNode l4_1 = new TreeNode(2, l5_1, r5_1);
        TreeNode r4_1 = new TreeNode(2, l5_2, r5_2);
        TreeNode l4_2 = new TreeNode(2, null, null);
        TreeNode r4_2 = new TreeNode(3, null, null);
        TreeNode l3 = new TreeNode(2, l4_1, r4_1);
        TreeNode r3 = new TreeNode(2, l4_2, r4_2);
        l2 = new TreeNode(2, l3, r3);
        r2 = new TreeNode(4, null, null);
        l1 = new TreeNode(2, l2, r2);
        r1 = new TreeNode(5, null, null);
        root = new TreeNode(2, l1, r1);
        System.out.println(findSecondMinimumValue(root));

        TreeNode l2_1 = new TreeNode(1, null, null);
        TreeNode r2_1 = new TreeNode(1, null, null);
        TreeNode l2_2 = new TreeNode(2, null, null);
        TreeNode r2_2 = new TreeNode(2, null, null);
        l1 = new TreeNode(1, l2_1, r2_1);
        r1 = new TreeNode(2, l2_2, r2_2);
        root = new TreeNode(1, l1, r1);
        System.out.println(findSecondMinimumValue(root));
    }

    private int ret;
    private int rootVal;

    public int findSecondMinimumValue2(TreeNode root) {
        ret = -1;
        rootVal = root.val;
        dfs(root);
        return ret;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (ret != -1 && node.val >= ret) {
            return;
        }
        if (node.val > rootVal) {
            ret = node.val;
        }
        dfs(node.left);
        dfs(node.right);
    }


    public static int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null) {
            return -1;
        }
        int rootVal = root.val;
        int secVal = findSecValForNode(root, null, rootVal);
        return secVal == rootVal ? -1 : secVal;
    }


    private static int findSecValForNode(TreeNode node, TreeNode neighbor, int rootVal) {
        if (node.left == null) {
            return Math.max(node.val, neighbor.val);
        }

        int secVal = Math.max(node.left.val, node.right.val);
        TreeNode cur, curNeighbor;
        if (node.left.val != node.right.val) {
            cur = node.left.val == rootVal ? node.left : node.right;
            curNeighbor = node.left.val == rootVal ? node.right : node.left;
            int val = findSecValForNode(cur, curNeighbor, rootVal);
            if (secVal == rootVal || (val < secVal && val != rootVal)) {
                secVal = val;
            }
        } else {
            int val = findSecValForNode(node.left, node.right, rootVal);
            if (secVal == rootVal || (val < secVal && val != rootVal)) {
                secVal = val;
            }
            val = findSecValForNode(node.right, node.left, rootVal);
            if (secVal == rootVal || (val < secVal && val != rootVal)) {
                secVal = val;
            }
        }
        return secVal;
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
