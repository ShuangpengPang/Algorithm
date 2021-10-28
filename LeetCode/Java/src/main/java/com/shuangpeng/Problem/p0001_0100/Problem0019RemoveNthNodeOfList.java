package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.ListNode;

public class Problem0019RemoveNthNodeOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int start = 0;
        ListNode current = head;
        ListNode h = null;
        while (current != null) {
            current = current.next;
            if (start >= n) {
                if (start == n) {
                    h = head;
                } else {
                    h = h.next;
                }
            }
            start++;
        }
        if (h == null) {
            return head.next;
        }
        h.next = h.next.next;
        return head;
    }

}
