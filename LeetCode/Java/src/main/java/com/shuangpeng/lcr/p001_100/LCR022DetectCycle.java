package com.shuangpeng.lcr.p001_100;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR022DetectCycle（环形链表II）
 * @date 2024/6/16 3:00 PM
 */
public class LCR022DetectCycle {

    public ListNode detectCycle0(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast && fast != null && fast.next != null);
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head.next;
        while (slow != fast && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = slow.next;
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
