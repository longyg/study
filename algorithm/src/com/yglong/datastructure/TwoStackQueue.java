package com.yglong.datastructure;

import java.util.Stack;

/**
 * 用两个栈实现一个先进先出的队列
 */
public class TwoStackQueue<E> {
    private Stack<E> pushStack;
    private Stack<E> popStack;

    public TwoStackQueue() {
        this.pushStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    private void pushToPop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public void add(E e) {
        pushStack.push(e);
        pushToPop();
    }

    public E poll() {
        if (pushStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return popStack.pop();
    }

    public E peek() {
        if (pushStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return popStack.peek();
    }

    public static void main(String[] args) {
        TwoStackQueue<Object> queue = new TwoStackQueue<>();
        queue.add(10);
        queue.add(5);
        queue.add(1);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
