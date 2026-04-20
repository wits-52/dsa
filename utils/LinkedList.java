package utils;

import entities.ListNode;

public final class LinkedList {
    public static <T> ListNode<T> fromArray(T[] arr) {
        ListNode<T> head = null, current = null;

        for (int i = 0; i < arr.length; i++) {
            ListNode<T> newNode = new ListNode<>(arr[i]);
            if (head == null) {
                head = newNode;
                current = newNode;
            } else {
                current.next = newNode;
                current = newNode;
            }
        }

        return head;
    }
    
    public static boolean equals(ListNode<Integer> head1, ListNode<Integer> head2) {
        ListNode<Integer> current1 = head1, current2 = head2;

        while (current1 != null && current2 != null) {
            if (!current1.val.equals(current2.val)) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        return current1 == null && current2 == null;
    }
}
