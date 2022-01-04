package com.yglong.leetcode.dailypractice._2021._11;

/**
 * 677. 键值映射
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/map-sum-pairs/
 */
public class Day_2021_11_14 {

    public static void main(String[] args) {
        MapSum m = new MapSum();
        m.insert("aa", 3);
        System.out.println(m.sum("a"));
        m.insert("ab", 2);
        System.out.println(m.sum("a"));
    }

    /**
     * 利用前缀树
     */
    static class MapSum {
        private TreeNode[] nodes = new TreeNode[26];

        public MapSum() {
        }

        public void insert(String key, int val) {
            int i = key.charAt(0) - 'a';
            TreeNode n = nodes[i];
            if (n == null) {
                n = new TreeNode();
                nodes[i] = n;
            }
            dfsInsert(n, key, 1, val);
        }

        public int sum(String prefix) {
            int i = prefix.charAt(0) - 'a';
            TreeNode root = nodes[i];
            if (root == null) return 0;
            return dfsSum(root, prefix, 1, 0);
        }

        private void dfsInsert(TreeNode node, String key, int k, int val) {
            if (k == key.length()) {
                node.val = val;
                return;
            }
            if (node.children == null) {
                node.children = new TreeNode[26];
            }
            int i = key.charAt(k) - 'a';
            TreeNode n = node.children[i];
            if (n == null) {
                n = new TreeNode();
                node.children[i] = n;
            }
            dfsInsert(n, key, k + 1, val);
        }

        private int dfsSum(TreeNode node, String prefix, int k, int curSum) {
            if (k < prefix.length()) {
                int i = prefix.charAt(k) - 'a';
                if (node.children == null) return 0;
                TreeNode n = node.children[i];
                if (n == null) return 0;
                return dfsSum(n, prefix, k + 1, 0);
            } else {
                if (k == prefix.length()) {
                    curSum = node.val;
                }
                if (node.children == null) return curSum;
                // 遍历所有已prefix为前缀的子树
                for (int i = 0; i < 26; i++) {
                    TreeNode n = node.children[i];
                    if (n != null) {
                        curSum = dfsSum(node.children[i], prefix, k + 1, curSum + n.val);
                    }
                }
            }
            return curSum;
        }
    }

    static class TreeNode {
        int val;
        TreeNode[] children;
    }
}
