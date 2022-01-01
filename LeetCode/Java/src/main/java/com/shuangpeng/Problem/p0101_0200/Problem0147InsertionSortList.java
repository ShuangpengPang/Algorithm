package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.ListNode;

import java.util.LinkedList;
import java.util.List;

public class Problem0147InsertionSortList {

    // TLE
    public ListNode insertionSortList0(ListNode head) {
        List<ListNode> list = new LinkedList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            ListNode curr = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).val > curr.val) {
                list.set(j + 1, list.get(j));
                --j;
            }
            list.set(j + 1, curr);
        }
        for (int i = 0; i < n - 1; ++i) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(n - 1).next = null;
        return list.get(0);
    }

    public ListNode insertionSortList1(ListNode head) {
        ListNode last = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            if (last == null || curr.val >= last.val) {
                curr.next = last;
                last = curr;
            } else {
                ListNode prev = last;
                ListNode temp = null;
                while (prev != null && prev.val > curr.val) {
                    temp = prev;
                    prev = prev.next;
                }
                temp.next = curr;
                curr.next = prev;
            }
            curr = next;
        }
        ListNode prev = null;
        curr = last;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode insertionSortList2(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode node = prev.next;
        while (node != null) {
            ListNode curr = dummy;
            while (curr.next != node && curr.next.val <= node.val) {
                curr = curr.next;
            }
            if (curr.next != node) {
                prev.next = node.next;
                node.next = curr.next;
                curr.next = node;
                node = prev.next;
            } else {
                prev = node;
                node = node.next;
            }
        }
        return dummy.next;
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode lastSorted = head;
        ListNode node = head.next;
        while (node != null) {
            if (lastSorted.val <= node.val) {
                lastSorted = node;
            } else {
                ListNode curr = dummy;
                while (curr.next.val <= node.val) {
                    curr = curr.next;
                }
                lastSorted.next = node.next;
                node.next = curr.next;
                curr.next = node;
            }
            node = lastSorted.next;
        }
        return dummy.next;
    }
}
