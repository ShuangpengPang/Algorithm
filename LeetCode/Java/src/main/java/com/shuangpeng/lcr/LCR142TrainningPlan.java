package com.shuangpeng.lcr;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR142TrainningPlan（训练计划IV）
 * @date 2024/5/16 5:31 PM
 */
public class LCR142TrainningPlan {

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
