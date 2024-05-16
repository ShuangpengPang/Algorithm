package com.shuangpeng.lcr;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR142TrainningPlan（训练计划IV）
 * @date 2024/5/16 5:31 PM
 */
public class LCR142TrainningPlan {

    public ListNode trainningPlan0(ListNode l1, ListNode l2) {
        return recurse(l1, l2);
    }

    private ListNode recurse(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val <= l2.val) {
            l1.next = recurse(l1.next, l2);
            return l1;
        } else {
            l2.next = recurse(l1, l2.next);
            return l2;
        }
    }

    public ListNode trainningPlan(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), node = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
