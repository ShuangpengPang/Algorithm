package com.shuangpeng.Problem.p0201_0300;

import com.shuangpeng.common.ListNode;

public class Problem0203RemoveLinkedListElements {

    public ListNode removeElements0(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode previous = dummy;
        ListNode node = head;
        while (node != null) {
            if (node.val == val) {
                previous.next = node.next;
            } else {
                previous = node;
            }
            node = node.next;
        }
        return dummy.next;
    }

    public ListNode removeElements1(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return dummy.next;
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return removeElements(head.next, val);
        }
        head.next = removeElements(head.next, val);
        return head;
    }
}
