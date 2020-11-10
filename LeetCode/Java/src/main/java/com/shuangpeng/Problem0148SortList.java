package com.shuangpeng;

public class Problem0148SortList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        Problem0148SortList a = new Problem0148SortList();
        ListNode head = new ListNode(3);
//        int[] array = {2, 1, 3};
        int[] array = {2, 4};
        ListNode current = head;
        for (int i = 0; i < array.length; i++) {
            ListNode node = new ListNode(array[i]);
            current.next = node;
            current = node;
        }
        a.sortList(head);
    }

    public ListNode sortList0(ListNode head) {
        return mergeSortList(head);
    }

    public ListNode mergeSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            } else {
                fast = fast.next;
            }
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = mergeSortList(head);
        ListNode right = mergeSortList(temp);
        ListNode l = left;
        ListNode r = right;
        ListNode h = null;
        ListNode cur = null;
        if (l.val <= r.val) {
            h = l;
            cur = l;
            l = l.next;
        } else {
            h = r;
            cur = r;
            r = r.next;
        }
        while (l != null || r != null) {
            if (l == null) {
                cur.next = r;
                break;
            }
            if (r == null) {
                cur.next = l;
                break;
            }
            if (l.val <= r.val) {
                cur.next = l;
                l = l.next;
            } else {
                cur.next = r;
                r = r.next;
            }
            cur = cur.next;
        }
        return h;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = 1;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        boolean isComplete = false;
        while (!isComplete) {
            ListNode start = dummyHead.next;
            ListNode currentHead = dummyHead;
            while (start != null) {
                ListNode end = start;
                ListNode mid = start;
                int i = 1;
                while (i < length * 2 && end.next != null) {
                    end = end.next;
                    if (i < length) {
                        mid = mid.next;
                    }
                    i++;
                }
                ListNode nextStart = end.next;
                if (mid != end) {
                    ListNode dummy = new ListNode(0);
                    ListNode current = dummy;
                    ListNode left = start;
                    ListNode right = mid.next;
                    mid.next = null;
                    end.next = null;
                    while (left != null && right != null) {
                        if (left.val < right.val) {
                            current.next = left;
                            current = left;
                            left = left.next;
                        } else {
                            current.next = right;
                            current = right;
                            right = right.next;
                        }
                    }
                    if (left == null) {
                        current.next = right;
                    } else {
                        current.next = left;
                    }
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = nextStart;
                    currentHead.next = dummy.next;
                    currentHead = current;
                } else {
                    isComplete = (start == dummyHead.next && mid == end);
                }
                start = nextStart;
            }
            length *= 2;
        }
        return dummyHead.next;
    }
}
