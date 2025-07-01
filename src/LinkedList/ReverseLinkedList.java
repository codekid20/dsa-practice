package LinkedList;

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    // Recursive Way
    public ListNode reverseList(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }

        ListNode newHead = reverseList(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;

        return newHead;
    }


    public ListNode reverseList1(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }

        ListNode prev = null;
        ListNode current = head;
        ListNode front = current.next;

        while (current != null){

            current.next = prev;
            prev = current;
            current = front;

            if(front != null){
                front = front.next;
            }
        }

        return prev;
    }
}
