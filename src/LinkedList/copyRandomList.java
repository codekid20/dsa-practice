package LinkedList;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val){
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class copyRandomList {

    public Node copyRandomList(Node head) {

        Node temp = head;

        while(temp != null){
            Node copy = new Node(temp.val);
            copy.next = temp.next;
            temp.next = copy;
            temp = temp.next.next;
        }

        temp = head;

        while (temp != null) {
            Node copy = temp.next;
            if(temp.random != null){
                copy.random = temp.random.next;
            }

            temp = temp.next.next;
        }

        Node dummy = new Node(-1);
        Node res = dummy;
        temp = head;

        while (temp != null) {
            res.next = temp.next;
            temp.next = temp.next.next;
            res = res.next;
            temp = temp.next;
        }

        return dummy.next;
    }
}
