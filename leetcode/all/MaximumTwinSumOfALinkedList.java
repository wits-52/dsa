package leetcode.all;

import entities.ListNode;

public class MaximumTwinSumOfALinkedList {
    public int pairSum(ListNode head) {
        int maxSum = 0; // all nodes are +ve so this is fine

        ListNode fast = head.next, slow = head;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode current = slow.next, previous = null;
        slow.next = null;

        while (current != null) {
            ListNode next = current.next;
            current.next = previous;

            previous = current;
            current = next;
        }

        while(head != null) {
            int sum = head.val + previous.val;

            maxSum = Math.max(maxSum, sum);

            head = head.next;
            previous = previous.next;
        }

        return maxSum;
    }
}
