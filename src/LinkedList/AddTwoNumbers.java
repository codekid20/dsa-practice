package LinkedList;

public class AddTwoNumbers {
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        int carry = 0;

        while (t1 != null || t2 != null){
            int sum = carry;

            if(t1 != null){
                sum += t1.val;
            }

            if(t2 != null){
                sum += t2.val;
            }

            ListNode newNode = new ListNode(sum % 10);
            current.next = newNode;
            current = current.next;
            carry = sum / 10;

            if(t1 != null){
                t1 = t1.next;
            }

            if(t2 != null){
                t2 = t2.next;
            }
        }

        if(carry == 1){
            ListNode newNode = new ListNode(carry);
            current.next = newNode;
            current = current.next;
        }

        return dummy.next;
    }
}
