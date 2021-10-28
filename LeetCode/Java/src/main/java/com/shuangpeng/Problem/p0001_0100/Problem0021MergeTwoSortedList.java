package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.ListNode;

public class Problem0021MergeTwoSortedList {

    // 递归解法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists0(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1, l1);
        ListNode previous = dummy;
        ListNode current = l1;
        ListNode current1 = l1;
        ListNode current2 = l2;

        while (current1 != null || current2 != null) {
            if (current1 == null || current2 == null) {
                if (current1 != null) {
                    previous.next = current1;
                } else {
                    previous.next = current2;
                }
                break;
            }
            int data1 = current1.val;
            int data2 = current2.val;
            ListNode node = current1;
            if (data1 > data2) {
                node = current2;
            }
            if (current == node) {
                previous = current;
                current = current.next;
            } else {
                previous.next = node;
                previous = node;
                current = node.next;
            }
            if (node == current1) {
                current1 = current1.next;
            } else {
                current2 = current2.next;
            }
        }
        return dummy.next;
    }
}
