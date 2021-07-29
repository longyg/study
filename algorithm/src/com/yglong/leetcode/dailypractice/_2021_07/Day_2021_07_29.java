package com.yglong.leetcode.dailypractice._2021_07;

import java.util.ArrayList;
import java.util.List;

/**
 * 1104. 二叉树寻路
 * <p>
 * <p>
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按“之” 字形进行标记。
 * <p>
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * <p>
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * <p>
 * <p>
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：label = 14
 * <p>
 * 输出：[1,3,4,14]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：label = 26
 * <p>
 * 输出：[1,2,6,10,26]
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= label <= 10^6
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree
 */
public class Day_2021_07_29 {

    public static void main(String[] args) {
        System.out.println(pathInZigZagTree(14));
        System.out.println(pathInZigZagTree(26));
    }


    public static List<Integer> pathInZigZagTree(int label) {
        List<Integer> list = new ArrayList<>();
        list.add(label);

        int pos = getReal(label); // label从左到右的实际位置，如果是偶数层，标记与实际位置相反
        while (pos > 1) {
            pos >>= 1;
            // 该位置的label值，如果是偶数层，位置与标记相反
            list.add(getReal(pos));
        }

        List<Integer> ret = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            ret.add(list.get(i));
        }

        return ret;
    }

    private static int getReal(int t) {
        int layer = ((int) (Math.log(t) / Math.log(2)) + 1);
        if (layer % 2 == 0) { // 偶数层
            // 如果是偶数层，则标记与实际位置相反
            return (1 << layer -1) + (1 << layer) - 1 - t;
        }
        return t;
    }
}
