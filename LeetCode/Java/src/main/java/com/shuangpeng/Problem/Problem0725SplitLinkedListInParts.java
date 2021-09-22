package com.shuangpeng.Problem;

import com.shuangpeng.common.ListNode;

public class Problem0725SplitLinkedListInParts {

    public ListNode[] splitListToParts0(ListNode head, int k) {
        int count = 0;
        ListNode node = head;
        while (node != null) {
            ++count;
            node = node.next;
        }
        int average = count / k;
        int mod = count % k;
        ListNode[] ans = new ListNode[k];
        node = head;
        for (int i = 0; i < mod; ++i) {
            ans[i] = node;
            ListNode previous = node;
            for (int j = 0; j < average + 1; ++j) {
                previous = node;
                node = node.next;
            }
            previous.next = null;
        }
        for (int i = mod; i < k; ++i) {
            ans[i] = node;
            if (node != null) {
                ListNode previous = node;
                for (int j = 0; j < average; ++j) {
                    previous = node;
                    node = node.next;
                }
                previous.next = null;
            }
        }
        return ans;
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            ++n;
            temp = temp.next;
        }
        int quotient = n / k, remainder = n % k;
        ListNode[] ans = new ListNode[k];
        ListNode curr = head;
        for (int i = 0; i < k && curr != null; ++i) {
            int size = quotient + (i < remainder ? 1 : 0);
            ans[i] = curr;
            for (int j = 1; j < size; ++j) {
                curr = curr.next;
            }
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        return ans;
    }
}
