package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.ListNode;

public class Problem0025ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode start = head;
        ListNode preEnd = new ListNode();
        ListNode dummy = preEnd;
        while (start != null) {
            ListNode end = start;
            int i = 1;
            while (i < k && end != null) {
                end = end.next;
                ++i;
            }
            if (i < k || end == null) {
                break;
            }
            ListNode pre = start, cur = start.next;
            while (pre != end) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            preEnd.next = pre;
            start.next = cur;
            preEnd = start;
            start = cur;
        }
        return dummy.next;
    }
}
