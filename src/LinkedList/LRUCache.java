package LinkedList;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache {

    static class DLL {
        int key;
        int value;

        DLL next;
        DLL prev;
    }

    private void addNode(DLL node) {

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }


    private void removeNode(DLL node) {

        DLL pre = node.prev;
        DLL post = node.next;

        pre.next = post;
        post.prev = pre;
    }

    private void moveToHead(DLL node) {
        removeNode(node);
        addNode(node);
    }

    private DLL popTail() {
        DLL res = tail.prev;
        removeNode(res);

        return res;
    }

    private final ConcurrentHashMap<Integer, DLL> cache = new ConcurrentHashMap<Integer, DLL>();

    private int count;
    private final int capacity;
    private final DLL head;
    private final DLL tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLL();
        head.prev = null;

        tail = new DLL();

        tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLL node = cache.get(key);

        if(node == null) return -1;
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {

        DLL node = cache.get(key);

        if(node == null) {
            DLL newnode = new DLL();
            newnode.key = key;
            newnode.value = value;

            cache.put(key, newnode);
            addNode(newnode);

            count++;

            if(count > capacity){
                DLL tail = popTail();
                cache.remove(tail.key);
                count--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
