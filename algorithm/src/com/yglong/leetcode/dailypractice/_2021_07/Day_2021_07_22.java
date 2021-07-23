package com.yglong.leetcode.dailypractice._2021_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 138. 复制带随机指针的链表
 * <p>
 * <p>
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 构造这个链表的深拷贝。深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * <p>
 * 返回复制链表的头节点。
 * <p>
 * <p>
 * 用一个由n个节点组成的链表来表示输入/输出中的链表。每个节点用一个[val, random_index]表示：
 * <p>
 * val：一个表示Node.val的整数。
 * <p>
 * random_index：随机指针指向的节点索引（范围从0到n-1）；如果不指向任何节点，则为null。
 * <p>
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [[1,1],[2,1]]
 * <p>
 * 输出：[[1,1],[2,1]]
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [[3,null],[3,0],[3,null]]
 * <p>
 * 输出：[[3,null],[3,0],[3,null]]
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * 输入：head = []
 * <p>
 * 输出：[]
 * <p>
 * 解释：给定的链表为空（空指针），因此返回 null。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 1000
 * <p>
 * -10000 <= Node.val <= 10000
 * <p>
 * Node.random为空（null）或指向链表中的节点。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 */
public class Day_2021_07_22 {
    public static void main(String[] args) {
        Node n0 = new Node(7);
        Node n1 = new Node(13);
        Node n2 = new Node(11);
        Node n3 = new Node(10);
        Node n4 = new Node(1);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        n1.random = n0;
        n2.random = n4;
        n3.random = n2;
        n4.random = n0;

        Node p = copyRandomList(n0);
        System.out.println(p);
    }

    /**
     * 常规解法：
     * 分别用ArrayList保存节点和复制节点，以及节点随机指针的索引
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // nodes保存所有节点
        List<Node> nodes = new ArrayList<>();
        nodes.add(head);
        Node p = head.next;
        while (p != null) {
            nodes.add(p);
            p = p.next;
        }

        // randomIndexes保存所有节点的随机指针指向的节点的索引
        // 如果随机指针为null，则设置索引为-1
        List<Integer> randomIndexes = new ArrayList<>();
        for (Node node : nodes) {
            if (node.random == null) {
                randomIndexes.add(-1);
            } else {
                randomIndexes.add(nodes.indexOf(node.random));
            }
        }

        // cNodes保存所有复制的节点
        List<Node> cNodes = new ArrayList<>();

        Node cHead = new Node(head.val);
        cNodes.add(cHead);

        p = head.next;
        Node cp = cHead;
        // 循环复制节点
        while (p != null) {
            Node cNode = new Node(p.val);
            cNodes.add(cNode);
            cp.next = cNode;
            cp = cp.next;
            p = p.next;
        }

        // 根据随机指针的索引，设置复制节点的随机指针
        for (int i = 0; i < randomIndexes.size(); i++) {
            int index = randomIndexes.get(i);
            cNodes.get(i).random = index == -1 ? null : cNodes.get(index);
        }
        return cHead;
    }

    /**
     * 哈希表 + 回溯，递归
     * 空间复杂度O(n)
     */
    private Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        if (!map.containsKey(head)) {
            Node newHead = new Node(head.val);
            map.put(head, newHead);
            newHead.next = copyRandomList2(head.next);
            newHead.random = copyRandomList2(head.random);
        }
        return map.get(head);
    }

    /**
     * 迭代 + 节点拆分
     * 空间复杂度O(1)
     */
    public static Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }

        // 构造一个长链表，包含原节点和拷贝的节点
        // 拷贝节点在原节点的后面
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }

        // 对拷贝节点设置random节点
        // 拷贝节点的random节点为原节点的random节点的next节点
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = node.next;
            newNode.random = (node.random != null) ? node.random.next : null;
        }

        // 拆分节点，将拷贝节点单独拆分出来形成一个新的链表
        Node newHead = head.next; // 新链表的头节点为长链表头节点的next节点
        for (Node node = head; node != null; node = node.next) {
            Node newNode = node.next;
            node.next = node.next.next;
            newNode.next = (newNode.next != null) ? newNode.next.next : null;
        }

        return newHead;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
