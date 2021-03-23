package com.yglong.datastructure.stack;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 */
public class SortStack {
    /**
     * 递归法
     *
     * @param stack
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(Stack<E> stack) {
        Stack<E> temp = new Stack<>();
        while (!stack.isEmpty()) {
            E e = getMax(stack);
            temp.push(e);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    private static <E extends Comparable<E>> E getMax(Stack<E> stack) {
        E max = stack.pop();
        if (!stack.isEmpty()) {
            E e = getMax(stack);
            if (e.compareTo(max) > 0) {
                stack.push(max);
                max = e;
            } else {
                stack.push(e);
            }

        }
        return max;
    }

    /**
     * 非递归法
     *
     * @param stack
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort2(Stack<E> stack) {
        Stack<E> temp = new Stack<>();
        while (!stack.isEmpty()) {
            E cur = stack.pop();
            while (!temp.isEmpty() && cur.compareTo(temp.peek()) > 0) {
                stack.push(temp.pop());
            }
            temp.push(cur);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(6);
        stack.push(5);
        stack.push(1);
        stack.push(3);
        stack.push(4);
        //sort(stack);
        sort2(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }


    }
}
