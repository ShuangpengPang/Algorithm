package com.shuangpeng.Problem;

import com.shuangpeng.common.ListNode;

public class Problem0142LinkedListCycle2 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = slow.next;
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}
