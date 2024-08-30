package com.shuangpeng.interview;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0205AddTwoNumbers（面试题 02.05. 链表求和）
 * @date 2024/8/30 11:29 AM
 */
public class Question0205AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), prev = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            prev.next = new ListNode(carry % 10);
            prev = prev.next;
            carry /= 10;
        }
        return dummy.next;
    }
}
