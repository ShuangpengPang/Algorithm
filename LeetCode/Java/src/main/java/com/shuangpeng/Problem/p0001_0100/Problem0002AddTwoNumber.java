package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.ListNode;

/**
 * @description: 两数相加
 * @date 2023/7/3 3:10 PM
 **/
public class Problem0002AddTwoNumber {

    public ListNode addTwoNumbers0(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = head;
        int carryBit = 0;
        while (l1 != null || l2 != null || carryBit > 0) {
            int sum = carryBit;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carryBit = sum / 10;
            sum %= 10;
            ListNode node = new ListNode(sum);
            if (current == null) {
                head = node;
            } else {
                current.next = node;
            }
            current = node;
        }
        return head;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        int carry = 0;
        for (ListNode node = dummy; l1 != null || l2 != null || carry > 0; carry /= 10, node = node.next) {
            carry += l1 == null ? 0 : l1.val;
            carry += l2 == null ? 0 : l2.val;
            node.next = new ListNode(carry % 10);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummy.next;
    }
}
