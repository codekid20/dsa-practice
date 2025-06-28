package StackandQueques;

import java.util.Queue;

public class QueueMain {
    public static void main(String[] args) throws Exception{
        CircularQueue queue = new CircularQueue(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);

        queue.display();
        System.out.println(queue.remove());
        queue.insert(6);
        queue.display();
        System.out.println(queue.remove());
        queue.insert(7);
        queue.display();
    }
}
