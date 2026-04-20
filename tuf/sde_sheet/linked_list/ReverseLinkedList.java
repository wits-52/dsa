package tuf.sde_sheet.linked_list;

import entities.ListNode;

public class ReverseLinkedList {
    public ListNode<Integer> reverseList(ListNode<Integer> head) {
        if (head == null) {
            return null;
        }
        ListNode<Integer> previous = null, current = head, next = head.next;

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
