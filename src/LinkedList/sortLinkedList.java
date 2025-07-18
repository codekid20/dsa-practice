package LinkedList;

import java.util.List;

public class sortLinkedList {

    private class ListNode {
        int val;
        ListNode next;
    }
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return merge(left, right);
    }

    ListNode merge(ListNode list1, ListNode list2) {
        ListNode merged = new ListNode();
        ListNode tail = merged;

        while (list1 != null && list2 != null) {
            if(list1.val < list2.val){
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }

            tail = tail.next;
        }

        tail.next = (list1 != null) ? list1 : list2;
        return merged.next;
    }

    // we have to break the list
    ListNode getMid(ListNode head){
        ListNode midPrev = null;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            midPrev = (midPrev == null) ? head : midPrev.next;
            fast = fast.next.next;
        }

        ListNode mid = midPrev.next;
        midPrev.next = null;

        return mid;
    }
}
