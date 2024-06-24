package com.shuangpeng.lcr.p001_100;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR026ReorderList（重排链表）
 * @date 2024/6/17 4:08 PM
 */
public class LCR026ReorderList {

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null, node = slow.next;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        slow.next = null;
        ListNode node1 = head, node2 = prev;
        while (node2 != null)  {
            ListNode next1 = node1.next, next2 = node2.next;
            node1.next = node2;
            node2.next = next1;
            node1 = next1;
            node2 = next2;
        }
    }
}
