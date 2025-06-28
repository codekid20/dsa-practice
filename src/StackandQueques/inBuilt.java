package StackandQueques;

import java.util.*;

public class inBuilt {
    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        System.out.println(stack.pop());

//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        System.out.println(queue.peek());

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        deque.addFirst(3);
        deque.addLast(4);
        System.out.println(deque.peek());
    }
}
