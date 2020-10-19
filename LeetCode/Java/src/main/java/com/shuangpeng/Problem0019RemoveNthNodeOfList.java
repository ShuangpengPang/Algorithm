package com.shuangpeng;

public class Problem0019RemoveNthNodeOfList {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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
