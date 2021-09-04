package com.shuangpeng.offer;

import com.shuangpeng.common.ListNode;

public class Offer22 {

    public ListNode getKthFromEnd0(ListNode head, int k) {
        int count = 0;
        ListNode node = head;
        while (node != null) {
            count++;
            node = node.next;
        }
        node = head;
        int c = count - k;
        for (int i = 0; i < c; ++i) {
            node = node.next;
        }
        return node;
    }

    public ListNode getKthFromEnd1(ListNode head, int k) {
        ListNode node = null;
        int n = 0;
        for (node = head; node != null; node = node.next) {
            n++;
        }
        for (node = head; n > k; --n) {
            node = node.next;
        }
        return node;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
