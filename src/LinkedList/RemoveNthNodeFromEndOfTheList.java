package LinkedList;

/*
Problem: Remove N-th Node from End of Linked List

Goal:
Given a linked list, remove the N-th node from the end in one pass.

Approach (Two-Pointer Technique):
1. Initialize two pointers: `fast` and `slow`, both at head.
2. Move `fast` pointer `n` steps ahead.
3. If `fast == null`, it means `n == length` ⇒ delete the head node.
4. Move both pointers one step at a time until `fast` reaches the end.
5. Now, `slow` is just before the node to be deleted ⇒ update `slow.next`.

Edge Case:
- When N equals the length of the list, we need to delete the head node.

Time: O(L), where L is the length of the list
Space: O(1)

Note:
- This is a classic example of the "fast and slow pointer" or "two pointer" pattern.
- Useful for many linked list problems involving distances or relative positions.
*/


public class RemoveNthNodeFromEndOfTheList {

    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // If deleting first Node
        // Basically If N is equal to length of the linked list
        if(fast == null){
            return head.next;
        }

        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return head;
    }
}
