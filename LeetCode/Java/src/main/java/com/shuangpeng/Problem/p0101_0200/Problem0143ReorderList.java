package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0143ReorderList {

    public void reorderList0(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            } else {
                break;
            }
        }
        ListNode pre = fast.next;
        ListNode node = slow;
        while (pre != fast) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        ListNode left = head, right = fast;
        while (right != slow) {
            ListNode nextLeft = left.next;
            ListNode nextRight = right.next;
            left.next = right;
            right.next = nextLeft;
            left = nextLeft;
            right = nextRight;
        }
    }

    public void reorderList1(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j - 1) {
            list.get(i).next = list.get(j);
            list.get(j).next = list.get(i + 1);
            ++i;
            --j;
        }
        list.get(j).next = null;
    }

    public void reorderList(ListNode head) {
        ListNode middle = getMiddleNode(head);
        ListNode right = reverseList(middle);
        ListNode left = head;
        while (left != middle && right != middle) {
            ListNode leftNext = left.next;
            ListNode rightNext = right.next;
            left.next = right;
            right.next = leftNext;
            left = leftNext;
            right = rightNext;
        }
    }

    private ListNode getMiddleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
