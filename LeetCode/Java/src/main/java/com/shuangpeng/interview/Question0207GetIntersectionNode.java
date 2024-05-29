package com.shuangpeng.interview;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0207GetIntersectionNode（面试题 02.07 链表相交）
 * @date 2024/5/29 10:30 AM
 */
public class Question0207GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode node1 = headA, node2 = headB;
        int cnt = 0;
        while (node1 != null && node2 != null && node1 != node2) {
            node1 = node1.next;
            if (node1 == null && cnt < 2) {
                node1 = headB;
                cnt++;
            }
            node2 = node2.next;
            if (node2 == null && cnt < 2) {
                node2 = headA;
                cnt++;
            }
        }
        return node1;
    }
}
