package com.yglong.datastructure;

import java.util.Stack;

public class MinStack2<E extends Comparable<E>> {

    // 用于存储所有数据
    private Stack<E> stack;
    // 用于存储最小值
    private Stack<E> minStack;

    public MinStack2() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(E e) {
        stack.push(e);
        if (minStack.isEmpty()) {
            minStack.push(e);
        } else {
            E minE = minStack.peek();
            if (e.compareTo(minE) <= 0) {
                minStack.push(e);
            }
        }
    }

    public E pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        E e = stack.pop();
        if (e.compareTo(getMin()) == 0) {
            minStack.pop();
        }
        return e;
    }

    public E getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack2<Integer> stack = new MinStack2<>();
        stack.push(5);
        stack.push(2);
        stack.push(1);
        stack.push(6);
        System.out.println(stack.getMin());

        System.out.println(stack.pop());

        System.out.println(stack.getMin());

        System.out.println(stack.pop());

        System.out.println(stack.getMin());

        stack.push(0);

        System.out.println(stack.getMin());

        System.out.println(stack.pop());
        System.out.println(stack.getMin());
    }
}
