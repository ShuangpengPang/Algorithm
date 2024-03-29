package com.shuangpeng.Problem.p0801_0900;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0876MiddleOfTheLinkedList（链表的中间结点）
 * @date 2024/1/12 11:43 PM
 */
public class Problem0876MiddleOfTheLinkedList {

    public ListNode middleNode0(ListNode head) {
        int cnt = 0;
        for (ListNode node = head; node != null; node = node.next) {
            cnt++;
        }
        ListNode node = head;
        for (int i = 0; i < cnt >> 1; i++) {
            node = node.next;
        }
        return node;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
