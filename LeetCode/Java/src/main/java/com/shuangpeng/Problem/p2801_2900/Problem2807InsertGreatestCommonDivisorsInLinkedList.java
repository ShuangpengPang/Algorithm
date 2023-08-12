package com.shuangpeng.Problem.p2801_2900;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2807InsertGreatestCommonDivisorsInLinkedList（在链表中插入最大公约数）
 * @date 2023/8/12 11:55 AM
 */
public class Problem2807InsertGreatestCommonDivisorsInLinkedList {

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            node.next = new ListNode(gcd(node.val, node.next.val), node.next);
            node = node.next.next;
        }
        return head;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
