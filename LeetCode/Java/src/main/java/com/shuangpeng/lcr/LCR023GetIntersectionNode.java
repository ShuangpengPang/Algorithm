package com.shuangpeng.lcr;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR023GetIntersectionNode（相交链表）
 * @date 2024/4/29 10:22 AM
 */
public class LCR023GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
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
}
