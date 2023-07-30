package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.ListNode;

/**
 * @description:（环形链表II）
 * @date 2023/7/30 5:34 PM
 **/
public class Problem0142LinkedListCycle2 {

    public ListNode detectCycle0(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow != fast) {
            return null;
        }
        int cnt = 1;
        ListNode node = slow.next;
        while (node != slow) {
            cnt++;
            node = node.next;
        }
        slow = fast = head;
        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

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
