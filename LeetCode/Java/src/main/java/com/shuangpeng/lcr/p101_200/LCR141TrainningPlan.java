package com.shuangpeng.lcr.p101_200;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR141TrainningPlan（训练计划III）
 * @date 2024/5/16 6:41 PM
 */
public class LCR141TrainningPlan {

    public ListNode trainningPlan0(ListNode head) {
        return recurse(head);
    }

    private ListNode recurse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode ans = recurse(node.next);
        node.next.next = node;
        node.next = null;
        return ans;
    }

    public ListNode trainningPlan(ListNode head) {
        ListNode prev = null, node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}
