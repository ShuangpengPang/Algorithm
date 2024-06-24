package com.shuangpeng.lcr.p001_100;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR023GetIntersectionNode（相交链表）
 * @date 2024/4/29 10:22 AM
 */
public class LCR023GetIntersectionNode {

    public ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
        for (ListNode node = headA; node != null; node = node.next) {
            node.val = -node.val;
        }
        ListNode ans = null;
        for (ListNode node = headB; node != null; node = node.next) {
            if (node.val < 0) {
                ans = node;
                break;
            }
        }
        for (ListNode node = headA; node != null; node = node.next) {
            node.val = -node.val;
        }
        return ans;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode node1 = headA, node2 = headB;
        while (node1 != node2) {
            node1 = node1 == null ? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }
}
