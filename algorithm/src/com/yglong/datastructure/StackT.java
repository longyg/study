package com.yglong.datastructure;

import java.util.Stack;

public class StackT {
    public static <E> E getAndRemoveLastElement(Stack<E> stack) {
        E e = stack.pop();
        if (!stack.isEmpty()) {
            E e1 = getAndRemoveLastElement(stack);
            stack.push(e);
            return e1;
        } else {
            return e;
        }
    }

    public static <E> void reverseS(Stack<E> stack) {
        if (stack.isEmpty()) {
            return;
        }
        E e1 = getAndRemoveLastElement(stack);
        reverseS(stack);
        stack.push(e1);
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(5);
        s.push(4);
        s.push(3);
        s.push(2);
        s.push(1);

        reverseS(s);

        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
