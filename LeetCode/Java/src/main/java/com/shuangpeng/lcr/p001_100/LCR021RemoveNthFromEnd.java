package com.shuangpeng.lcr.p001_100;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR021RemoveNthFromEnd（删除链表的倒数第N个结点）
 * @date 2024/6/16 5:03 PM
 */
public class LCR021RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy, second = head;
        for (int i = 0; i < n; i++) {
            second = second.next;
        }
        while (second != null) {
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}
