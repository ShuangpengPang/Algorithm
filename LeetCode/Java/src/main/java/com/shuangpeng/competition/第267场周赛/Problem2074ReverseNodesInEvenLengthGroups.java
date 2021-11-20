package com.shuangpeng.competition.第267场周赛;

import com.shuangpeng.common.ListNode;

public class Problem2074ReverseNodesInEvenLengthGroups {

    // 比赛时写法
    public ListNode reverseEvenLengthGroups0(ListNode head) {
        int i = 1;
        ListNode start = head;
        boolean toEnd = false;
        ListNode last = head;
        while (start != null && !toEnd) {
            ListNode end = start;
            int j = 1;
            for (; j < i && end.next != null; ++j) {
                end = end.next;
            }
            if (end.next == null) {
                toEnd = true;
            }
            if ((j & 1) == 0) {
                ListNode pre = end.next;
                ListNode cur = start;
                while (pre != end) {
                    ListNode next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                last.next = end;
                last = start;
                start = start.next;
            } else {
                last = end;
                start = end.next;
            }
            ++i;
        }
        return head;
    }

    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode start = head;
        ListNode last = new ListNode();
        int i = 1;
        while (start != null) {
            ListNode end = start;
            int j = 1;
            while (j < i && end.next != null) {
                end = end.next;
                ++j;
            }
            if ((j & 1) == 0) {
                ListNode pre = end.next;
                ListNode cur = start;
                while (pre != end) {
                    ListNode next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                last.next = end;
                last = start;
                start = start.next;
            } else {
                last = end;
                start = end.next;
            }
            ++i;
        }
        return head;
    }
}
