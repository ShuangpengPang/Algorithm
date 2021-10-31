package com.shuangpeng.Problem.p0201_0300;

import com.shuangpeng.common.ListNode;

public class Problem0237DeleteNodeInALinkedList {

    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
