package com.shuangpeng.Problem.p0301_0400;

import com.shuangpeng.common.ListNode;

public class Problem0328OddEventLinkedList {

    public ListNode oddEvenList0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h = head.next;
        ListNode even = h;
        ListNode node = head;
        while (node.next != null) {
            ListNode temp = node.next;
            node.next = temp.next;
            if (even != temp) {
                even.next = temp;
                even = temp;
            }
            if (node.next != null) {
                node = node.next;
            }
        }
        node.next = h;
        even.next = null;
        return head;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
