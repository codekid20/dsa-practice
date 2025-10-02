package StackandQueques;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Deque_ArrayDeque {
    public static void main(String[] args) {

        Deque<Integer> dq = new LinkedList<>();
        dq.offer(1);
        dq.offer(2);
        dq.add(3);
        dq.addLast(4);
        System.out.println(dq.peekLast());


        Deque<Integer> dq1 = new ArrayDeque<>();
        dq1.add(1);
    }
}
