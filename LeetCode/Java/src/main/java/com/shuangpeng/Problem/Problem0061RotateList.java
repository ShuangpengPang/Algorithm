package com.shuangpeng.Problem;

public class Problem0061RotateList {

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

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int count = 0;
        ListNode current = head;
        ListNode tail = head;
        while (current != null) {
            tail = current;
            current = current.next;
            count++;
        }
        int target = count - k % count;
        if (target == count) {
            return head;
        }
        ListNode previous = null;
        ListNode node = head;
        for (int i = 0; i < target; i++) {
            previous = node;
            node = node.next;
        }
        if (previous != null) {
            previous.next = null;
        }
        tail.next = head;
        return node;
    }
}
