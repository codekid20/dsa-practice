package LinkedList;

// 🚀 SLOW & FAST POINTER TECHNIQUE - SINGLE PAGE SUMMARY
// ==========================================
// Tortoise-Hare approach to detect patterns in linked lists or linked-like structures (arrays with index jumps)

class SlowFastExamples {

    // 1️⃣ Detect Cycle in Linked List (Floyd’s Algorithm)
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;             // move 1 step
            fast = fast.next.next;       // move 2 steps
            if (slow == fast) return true; // cycle detected
        }
        return false;
    }

    // 2️⃣ Find Start of Cycle in Linked List
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;             // reset slow
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;             // cycle start
            }
        }
        return null;
    }

    // 3️⃣ Find Middle Node of Linked List
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // for even length: returns 2nd middle
    }

    // 4️⃣ Find Duplicate Number in Array (Floyd on index jump)
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];              // move 1 step
            fast = nums[nums[fast]];        // move 2 steps
        } while (slow != fast);

        slow = nums[0];                     // reset slow
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;                        // duplicate number
    }

    // 5️⃣ Check if Linked List is a Palindrome
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // Step 1: Find middle
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode secondHalf = reverse(slow.next);

        // Step 3: Compare both halves
        ListNode p1 = head, p2 = secondHalf;
        while (p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    // Helper for reverse
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // 6️⃣ Find Kth Node from End
    public ListNode kthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) fast = fast.next;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // 7️⃣ Intersection of Two Linked Lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }
}
