package LinkedList;

public class MergeTwoSortedList {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode ans = new ListNode();
        ListNode head = ans;

        while (list1 != null && list2 != null){
            if(list1.val < list2.val){
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }

            head = head.next;
        }

        head.next = list1 != null ? list1 : list2;

        return ans.next;
    }
}
