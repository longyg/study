package com.yglong.problems;

import java.util.Stack;

/**
 * 汉诺塔问题
 */
public class HanoiProblem {
    private static final String LEFT = "left";
    private static final String MID = "mid";
    private static final String RIGHT = "right";

    /**
     * 递归法
     * @param n
     * @return
     */
    public static int move(int n) {
        if (n < 1) {
            return 0;
        }
        return process(n, LEFT, RIGHT);
    }

    public static int process(int n, String from, String to) {
        if (n == 1) {
            if (MID.equals(from) || MID.equals(to)) {
                System.out.println("Move " + n + " from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move " + n + " from " + from + " to " + MID);
                System.out.println("Move " + n + " from " + MID + " to " + to);
                return 2;
            }
        }
        if (MID.equals(from) || MID.equals(to)) {
            String another = (LEFT.equals(from) || LEFT.equals(to)) ? RIGHT : LEFT;
            int part1 = process(n - 1, from, another);
            int part2 = 1;
            System.out.println("Move " + n + " from " + from + " to " + to);
            int part3 = process(n - 1, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = process(n - 1, from, to);
            int part2 = 1;
            System.out.println("Move " + n + " from " + from + " to " + MID);
            int part3 = process(n - 1, to, from);
            int part4 = 1;
            System.out.println("Move " + n + " from " + MID + " to " + to);
            int part5 = process(n - 1, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    /**
     * 非递归法，利用3个栈模拟3个塔
     * 找出所有规则，每一步判断是否满足所有规则，只有满足才能移动，每移动一次累加1
     * @param n
     * @return
     */
    public static int move2(int n) {
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();
        ls.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);
        for (int i = n; i > 0; i--) {
            ls.push(i);
        }
        Action[] records = {Action.No};
        int step = 0;
        while(rs.size() != n + 1) {
            step += fStackToStack(records, Action.MToL, Action.LToM, ls, ms, LEFT, MID);
            step += fStackToStack(records, Action.LToM, Action.MToL, ms, ls, MID, LEFT);
            step += fStackToStack(records, Action.MToR, Action.RToM, rs, ms, RIGHT, MID);
            step += fStackToStack(records, Action.RToM, Action.MToR, ms, rs, MID, RIGHT);
        }
        return step;
    }

    public static int fStackToStack(Action[] records, Action preAct, Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack, String from, String to) {
        if (records[0] != preAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            records[0] = nowAct;
            return 1;
        }
        return 0;
    }

    public enum Action {
        No, LToM, MToL, MToR, RToM
    }

    public static void main(String[] args) {
        // System.out.println("Total steps: " + move(3));
        System.out.println("Total steps: " + move2(3));
    }
}
