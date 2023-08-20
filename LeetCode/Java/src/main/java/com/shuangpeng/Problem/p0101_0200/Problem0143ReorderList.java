package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:（重排链表）
 * @date 2023/7/31 11:01 AM
 **/
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
        while (i < j) {
            list.get(i).next = list.get(j);
            if (i + 1 < j) {
                list.get(j).next = list.get(i + 1);
            }
            i++;
            j--;
        }
        list.get(i).next = null;
    }

    public void reorderList2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int n = list.size();
        for (int i = 0; i < n; i++) {
            int j = i <= n - 1 >> 1 ? n - i - 1 : n - i;
            list.get(i).next = i == j ? null : list.get(j);
        }
    }

    public void reorderList(ListNode head) {
        ListNode middle = getMidNode(head);
        ListNode left = head, right = reverse(middle);
        while (left != middle) {
            ListNode nextLeft = left.next, nextRight = right.next;
            left.next = right;
            if (nextLeft != right) {
                right.next = nextLeft;
            }
            left = nextLeft;
            right = nextRight;
        }
    }

    private ListNode getMidNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}

class Problem0143ReorderList0 {

    ListNode cur, prev;
    public void reorderList(ListNode head) {
        reorderList(head, head);
    }
    public void reorderList(ListNode slow, ListNode fast) {
        if (fast == null) { //处理链表长度为偶数的情况
            cur = slow; //需要插入的第一个节点的前一个节点
            prev = null;
            return;
        }
        if (fast.next == null) { //处理链表长度为奇数的情况
            cur = slow.next; //需要插入的第一个节点
            slow.next = null;
            prev = slow; //重排后的最后一个节点
            return;
        }
        reorderList(slow.next, fast.next.next);
        ListNode next = cur.next;
        //节点交换
        slow.next = cur;
        cur.next = prev;
        prev = slow;
        cur = next;
    }
}

class Problem0143ReorderList1 {

    public void reorderList(ListNode head) {
        ListNode mid = getMid(head), node2 = reverse(mid.next);
        mid.next = null;
        ListNode node1 = head;
        while (node2 != null) {
            ListNode next1 = node1.next, next2 = node2.next;
            node1.next = node2;
            node2.next = next1;
            node1 = next1;
            node2 = next2;
        }
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
