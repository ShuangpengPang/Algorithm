package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.ListNode;
import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * @description:（合并K个排序链表）
 * @date 2023/8/12 11:28 AM
 **/
public class Problem0023MergeKSortedList {

    public ListNode mergeKLists0(ListNode[] lists) {
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

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int length = lists.length;
        ListNode dummy = new ListNode();
        dummy.next = lists[0];
        for (int i = 1; i < length; i++) {
            if (lists[i] == null) {
                continue;
            }
            ListNode current = dummy;
            ListNode node1 = dummy.next;
            ListNode node2 = lists[i];
            while (node1 != null && node2 != null) {
                if (node1.val < node2.val) {
                    current.next = node1;
                    current = node1;
                    node1 = node1.next;
                } else {
                    current.next = node2;
                    current = node2;
                    node2 = node2.next;
                }
            }
            while (node1 != null) {
                current.next = node1;
                current = node1;
                node1 = node1.next;
            }
            while (node2 != null) {
                current.next = node2;
                current = node2;
                node2 = node2.next;
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int length = lists.length;
        PriorityQueue<Pair<Integer, ListNode>> queue = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        for (int i = 0; i < length; i++) {
            ListNode head = lists[i];
            ListNode current = head;
            int count = 0;
            while (current != null) {
                count++;
                current = current.next;
            }
            if (count > 0) {
                queue.offer(new Pair<>(count, head));
            }
        }
        while (queue.size() > 1) {
            Pair<Integer, ListNode> pair1 = queue.poll();
            Pair<Integer, ListNode> pair2 = queue.poll();
            queue.offer(new Pair<>(pair1.getKey() + pair2.getKey(), mergeTwoList(pair1.getValue(), pair2.getValue())));
        }
        if (!queue.isEmpty()) {
            return queue.poll().getValue();
        }
        return null;
    }

    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    public ListNode divideAndConquer(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = (start + end) >>> 1;
        ListNode left = divideAndConquer(lists, start, mid);
        ListNode right = divideAndConquer(lists, mid + 1, end);
        return mergeTwoList(left, right);
    }

    public ListNode mergeTwoList(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                current.next = node1;
                node1 = node1.next;
            } else {
                current.next = node2;
                node2 = node2.next;
            }
            current = current.next;
        }
        current.next = node1 == null ? node2 : node1;
        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) {
                q.offer(node);
            }
        }
        ListNode dummy = new ListNode(), node = dummy;
        while (!q.isEmpty()) {
            ListNode cur = q.poll();
            node.next = cur;
            if (cur.next != null) {
                q.offer(cur.next);
            }
            node = cur;
        }
        return dummy.next;
    }
}
