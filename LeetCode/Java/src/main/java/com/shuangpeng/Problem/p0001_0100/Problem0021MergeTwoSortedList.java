package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.ListNode;

/**
 * @description:（合并两个有序链表）
 * @date 2023/8/5 7:16 PM
 **/
public class Problem0021MergeTwoSortedList {

    // 递归解法
    public ListNode mergeTwoLists0(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists0(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists0(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
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

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        return dfs(list1, list2);
    }

    private ListNode dfs(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = dfs(list1.next, list2);
            return list1;
        }
        list2.next = dfs(list1, list2.next);
        return list2;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0), node = dummy;
        while (list1 != null || list2 != null) {
            if (list2 == null || list1 != null && list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        return dummy.next;
    }
}
