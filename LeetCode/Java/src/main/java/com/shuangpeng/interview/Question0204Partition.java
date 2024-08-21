package com.shuangpeng.interview;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0204Partition（面试题 02.04. 分割链表）
 * @date 2024/8/21 4:05 PM
 */
public class Question0204Partition {

    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;
        for (ListNode node = head; node != null; node = node.next) {
            if (node.val < x) {
                int t = p.next.val;
                p.next.val = node.val;
                node.val = t;
                p = p.next;
            }
        }
        return dummy.next;
    }
}
