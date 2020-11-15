package com.shuangpeng;

public class Problem234PalindromeLinkedList {

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

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        int half = count / 2;
        ListNode previous = null;
        ListNode current = head;
        for (int i = 0; i < half; i++) {
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        if ((count & 1) == 1) {
            current = current.next;
        }
        for (int i = 0; i < half; i++) {
            if (previous.val != current.val) {
                return false;
            }
            previous = previous.next;
            current = current.next;
        }
        return true;
    }
}
