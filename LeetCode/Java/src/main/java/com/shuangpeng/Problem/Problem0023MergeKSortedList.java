package com.shuangpeng.Problem;

public class Problem0023MergeKSortedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return recursive(lists, lists.length);
    }

    public ListNode recursive(ListNode[] lists, int length) {
        if (length == 1) {
            return lists[0];
        }
        int data = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < length; i++) {
            ListNode node = lists[i];
            if (node != null && node.val < data) {
                data = node.val;
                index = i;
            }
        }
        if (index == -1) {
            return null;
        }
        ListNode head = lists[index];
        if (head.next == null) {
            for (int i = index + 1; i < length; i++) {
                lists[i - 1] = lists[i];
            }
            length--;
        } else {
            lists[index] = head.next;
        }
        head.next = recursive(lists, length);
        return head;
    }
}
