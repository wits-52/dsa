package tuf.sde_sheet.linked_list;

import entities.ListNode;

public class MergeTwoSortedLists {
    public ListNode<Integer> mergeTwoLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> head, curr;

        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            head = list1;
            curr = list1;
            list1 = list1.next;
        } else {
            head = list2;
            curr = list2;
            list2 = list2.next;
        }

        while(list1 != null && list2 != null) {
            // keep setting next
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        while (list1 != null) {
            curr.next = list1;
            list1 = list1.next;
            curr = curr.next;
        }
        while (list2 != null) {
            curr.next = list2;
            list2 = list2.next;
            curr = curr.next;
        }

        return head;
    }
}