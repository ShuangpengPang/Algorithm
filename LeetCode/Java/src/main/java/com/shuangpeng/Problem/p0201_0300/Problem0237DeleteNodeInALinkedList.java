package com.shuangpeng.Problem.p0201_0300;

import com.shuangpeng.common.ListNode;

public class Problem0237DeleteNodeInALinkedList {

    public void deleteNode0(ListNode node) {
        if (node == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public void deleteNode(ListNode node) {
        ListNode pre = null;
        while (node.next != null) {
            node.val = node.next.val;
            pre = node;
            node = node.next;
        }
        pre.next = null;
    }
}
