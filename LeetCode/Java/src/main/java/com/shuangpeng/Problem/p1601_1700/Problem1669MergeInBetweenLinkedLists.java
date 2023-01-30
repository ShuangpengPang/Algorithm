package com.shuangpeng.Problem.p1601_1700;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1669MergeInBetweenLinkedLists（合并两个链表）
 * @date 2023/1/30 12:13 PM
 */
public class Problem1669MergeInBetweenLinkedLists {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
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
}
