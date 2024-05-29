package com.shuangpeng.interview;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0206IsPalindrome（面试题 02.06. 回文链表）
 * @date 2024/5/29 11:14 AM
 */
public class Question0206IsPalindrome {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode node = slow.next, prev = null, cur = head;
        while (cur != node) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        if (fast.next != null) {
            node = node.next;
        }
        while (prev != null && prev.val == node.val) {
            prev = prev.next;
            node = node.next;
        }
        return prev == null;
    }
}
