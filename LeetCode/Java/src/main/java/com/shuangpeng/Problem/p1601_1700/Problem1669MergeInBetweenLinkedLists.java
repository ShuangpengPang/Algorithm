package com.shuangpeng.Problem.p1601_1700;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1669MergeInBetweenLinkedLists（合并两个链表）
 * @date 2023/1/30 12:13 PM
 */
public class Problem1669MergeInBetweenLinkedLists {

    public ListNode mergeInBetween0(ListNode list1, int a, int b, ListNode list2) {
        ListNode node1 = list1;
        for (int i = 1; i < a; i++) {
            node1 = node1.next;
        }
        ListNode node2 = node1.next;
        for (int i = a; i <= b; i++) {
            node2 = node2.next;
        }
        node1.next = list2;
        while (node1.next != null) {
            node1 = node1.next;
        }
        node1.next = node2;
        return list1;
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode n1 = list1, n2 = list1;
        int gap = b - a + 1;
        for (int i = 0; i <= b; i++) {
            if (i > gap) {
                n1 = n1.next;
            }
            n2 = n2.next;
        }
        n1.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = n2;
        return list1;
    }
}
