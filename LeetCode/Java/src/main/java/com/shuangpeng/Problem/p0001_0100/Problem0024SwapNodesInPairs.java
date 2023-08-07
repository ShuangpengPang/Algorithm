package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.ListNode;

/**
 * @description:（两两交换链表中的节点）
 * @date 2023/8/7 11:32 AM
 **/
public class Problem0024SwapNodesInPairs {

    public ListNode swapPairs0(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode next = node.next.next;
            ListNode temp = node.next;
            node.next = next;
            temp.next = node;
            prev.next = temp;
            prev = node;
            node = next;
        }
        return dummy.next;
    }

    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(0, head), prev = dummy;
        for (ListNode node = head; node != null && node.next != null; node = node.next) {
            ListNode next = node.next.next;
            prev.next = node.next;
            node.next.next = node;
            node.next = next;
            prev = node;
        }
        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = next.next;
        next.next = head;
        head.next = swapPairs(head.next);
        return next;
    }
}
