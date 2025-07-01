package LinkedList;

public class ReorderLinkedList {
    public static void main(String[] args) {

    }

    public void reorderList(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = reverseList(slow);
        ListNode first = head;

        while(first != null && second != null){
            ListNode temp = first.next;      // Store next node from first
            first.next = second;             // Link first → second
            first = temp;                    // Move first forward

            temp = second.next;             // Store next node from second
            second.next = first;            // Link second → first
            second = temp;                  // Move second forward
        }

        if(first != null){
            first.next = null;
        }
    }

    public ListNode reverseList(ListNode head) {

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
