package tuf.sde_sheet.linked_list;

import entities.ListNode;

public class MiddleOfTheLinkedList {
    public ListNode<Integer> middleNode(ListNode<Integer> head) {
        ListNode<Integer> slow = new ListNode<>(), fast = new ListNode<>();

        slow = head;
        fast = head.next;

        while (fast != null) {
            slow = slow.next;

            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }

        return slow;
    }
}
