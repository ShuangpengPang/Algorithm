package com.shuangpeng.Problem;

import com.shuangpeng.common.ListNode;

public class Problem0061RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int count = 0;
        ListNode current = head;
        ListNode tail = head;
        while (current != null) {
            tail = current;
            current = current.next;
            count++;
        }
        int target = count - k % count;
        if (target == count) {
            return head;
        }
        ListNode previous = null;
        ListNode node = head;
        for (int i = 0; i < target; i++) {
            previous = node;
            node = node.next;
        }
        if (previous != null) {
            previous.next = null;
        }
        tail.next = head;
        return node;
    }
}
