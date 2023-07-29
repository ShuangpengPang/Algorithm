package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:（环形链表）
 * @date 2023/7/29 11:43 AM
 **/
public class Problem0141LinkedListCycle {

    public boolean hasCycle0(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        for (ListNode node = head; node != null; node = node.next) {
            if (!visited.add(node)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (slow != fast && fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return fast != null && fast.next != null && slow == fast;
    }
}
