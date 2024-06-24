package com.shuangpeng.lcr.p101_200;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR136DeleteNode（删除链表的节点）
 * @date 2024/5/14 12:11 PM
 */
public class LCR136DeleteNode {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head), node = dummy;
        while (node.next != null && node.next.val != val) {
            node = node.next;
        }
        if (node.next != null) {
            node.next = node.next.next;
        }
        return dummy.next;
    }
}
