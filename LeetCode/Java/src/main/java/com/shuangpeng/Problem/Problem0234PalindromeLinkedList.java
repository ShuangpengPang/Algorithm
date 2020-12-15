package com.shuangpeng.Problem;

import com.shuangpeng.common.ListNode;

public class Problem0234PalindromeLinkedList {

    public boolean isPalindrome0(ListNode head) {
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

    // 递归解法
    ListNode listNode = null;
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        listNode = head;
        return checkPalindrome(head.next);
    }

    public boolean checkPalindrome(ListNode node) {
        if (node == null) {
            return true;
        }
        if (node == listNode) {
            return true;
        }
        if (checkPalindrome(node.next) && listNode.val == node.val) {
            listNode = listNode.next;
            return true;
        }
        return false;
    }

    // 快慢指针
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode previous = null;
        while (fast != null && fast.next != null) {
            ListNode temp = slow.next;
            slow.next = previous;
            previous = slow;
            slow = temp;
            fast = fast.next.next;
        }
        ListNode left = slow;
        ListNode right = slow.next;
        slow.next = previous;
        if (fast == null) {
            left = previous;
        }
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode previous = null;
        while (fast != null && fast.next != null) {
            previous = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode left = slow;
        ListNode right = slow.next;
        slow.next = null;
        if (fast == null) {
            // 奇数
            left = previous;
        }
        reverseList(head);
        boolean isValid = true;
        ListNode l = left;
        ListNode r = right;
        while (l != null) {
            if (l.val != r.val) {
                isValid = false;
                break;
            }
            l = l.next;
            r = r.next;
        }
        reverseList(slow);
        slow.next = right;
        return isValid;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        return previous;
    }
}
