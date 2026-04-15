package leetcode.all;

import entities.ListNode;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;

        ListNode previous = null, current = head;

        while (current != null) {
            ListNode next = current.next;
            current.next = previous;
            
            previous = current;
            current = next;
        }

        return previous;
    }
}
