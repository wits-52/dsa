package tuf.sde_sheet.linked_list;

import utils.LinkedList;

import entities.ListNode;
import utils.TestUtil;

public class RemoveNthNodeFromEndOfList {
    public ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int n) {
        if (head == null) return head;

        int length = 0;
        ListNode<Integer> current = head, previous = null, next = head.next;

        while (current != null) {
            length++;
            current = current.next;
        }
        current = head;
        for (int i = 1; i <= length - n; i++) {
            previous = current;
            current = current.next;
            if (current != null) {
                next = current.next;
            }
        }
        if (previous == null) {
            return head.next;
        }
        previous.next = next;
        current.next = null;

        return head;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();

        TestUtil.run(
            "Test Case #1",
            LinkedList.equals(
                solution.removeNthFromEnd(LinkedList.fromArray(new Integer[]{1,2,3,4,5}), 2),
                LinkedList.fromArray(new Integer[]{1,2,3,5})
            )
        );
        TestUtil.run(
            "Test Case #2",
            LinkedList.equals(
                solution.removeNthFromEnd(LinkedList.fromArray(new Integer[]{1,2,3,4,5}), 1),
                LinkedList.fromArray(new Integer[]{1,2,3,4})
            )
        );
        TestUtil.run(
            "Test Case #3",
            LinkedList.equals(
                solution.removeNthFromEnd(LinkedList.fromArray(new Integer[]{1,2,3,4,5}), 5),
                LinkedList.fromArray(new Integer[]{2,3,4,5})
            )
        );
    }
}
