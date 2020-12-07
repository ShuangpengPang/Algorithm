package com.shuangpeng.Problem;

public class Problem0237DeleteNodeInALinkedList {

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

    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
