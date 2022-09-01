package com.shuangpeng.competition.第261到270场周赛.第270场周赛;

import com.shuangpeng.common.ListNode;

public class Problem2095DeleteTheMiddleNodeOfALinkedList {

    // 比赛时写法
    public ListNode deleteMiddle0(ListNode head) {
        int count = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            ++count;
        }
        if (count == 1) {
            return null;
        }
        int half = count >> 1;
        int i = 1;
        node = head;
        while (i < half) {
            node = node.next;
            ++i;
        }
        node.next = node.next.next;
        return head;
    }

    public ListNode deleteMiddle1(ListNode head) {
        ListNode node = head;
        int n = 0;
        while (node != null) {
            ++n;
            node = node.next;
        }
        if (n == 1) {
            return null;
        }
        int target = (n >> 1) - 1;
        int i = 0;
        node = head;
        while (i < target) {
            node = node.next;
            ++i;
        }
        node.next = node.next.next;
        return head;
    }

    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }
        ListNode fast = head.next.next, slow = head.next, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = pre.next.next;
        return head;
    }
}
