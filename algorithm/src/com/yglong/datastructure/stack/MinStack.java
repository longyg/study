package com.yglong.datastructure.stack;

import java.util.Stack;

/**
 * 构造一个自定义栈，使得获取栈的最小值的时间复杂度为O(1)
 * <p>
 * 使用两个栈，其中一个栈用于保存最小值
 */
public class MinStack<E extends Comparable<E>> {
    // 用于存储所有数据
    private Stack<E> stack;
    // 用于存储最小值
    private Stack<E> minStack;

    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(E e) {
        stack.push(e);
        if (minStack.isEmpty()) {
            minStack.push(e);
        } else {
            E e1 = minStack.peek();
            if (e1.compareTo(e) < 0) {
                minStack.push(e1);
            } else {
                minStack.push(e);
            }
        }
    }

    public E pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        minStack.pop();
        return stack.pop();
    }

    public E getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack<Integer> stack = new MinStack<>();
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

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}
