package com.yglong.leetcode.dailypractice._2021._06;

import java.util.*;

/**
 * 752. 打开转盘锁
 * <p>
 * <p>
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。
 * 每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * <p>
 * 输出：6
 * <p>
 * 解释：
 * <p>
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * <p>
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * <p>
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * <p>
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 */
public class Day_2021_06_25 {
    public static void main(String[] args) {
        System.out.println(openLock(new String[] {"0201","0101","0102","1212","2002"}, "0202"));
    }

    public static int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return 0;
        }
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains("0000")) {
            return -1;
        }

        int step = 0;
        LinkedList<String> queue = new LinkedList<>();
        queue.offer("0000");
        HashSet<String> done = new HashSet<>(); // 已搜索过的数字
        done.add("0000");

        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String status = queue.poll();
                for (String newStatus : get(status)) {
                    if (!done.contains(newStatus) && !deadSet.contains(newStatus)) {
                        if (newStatus.equals(target)) {
                            return step;
                        }
                        queue.offer(newStatus);
                        done.add(newStatus);
                    }
                }
            }
        }

        return -1;
    }

    private static List<String> get(String status) {
        List<String> list = new ArrayList<>();
        char[] chars = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char c = chars[i];
            chars[i] = prevNum(c);
            list.add(new String(chars));
            chars[i] = nextNum(c);
            list.add(new String(chars));
            chars[i] = c;
        }
        return list;
    }

    private static char prevNum(char c) {
        return c == '0' ? '9' : (char) (c - 1);
    }

    private static char nextNum(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }
}
