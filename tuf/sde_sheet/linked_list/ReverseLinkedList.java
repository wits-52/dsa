package tuf.sde_sheet.linked_list;

import entities.ListNode;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode previous = null, current = head, next = head.next;

        while(next != null) {
            current.next = previous;
            previous = current;
            current = next;
            next = current.next;
        }

        current.next = previous;

        return current;
    }
}
