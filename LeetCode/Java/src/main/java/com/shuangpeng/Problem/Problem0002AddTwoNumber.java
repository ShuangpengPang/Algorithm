package com.shuangpeng.Problem;

public class Problem0002AddTwoNumber {

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = head;
        int carryBit = 0;
        while (l1 != null || l2 != null || carryBit > 0) {
            int sum = carryBit;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carryBit = sum / 10;
            sum %= 10;
            ListNode node = new ListNode(sum);
            if (current == null) {
                head = node;
            } else {
                current.next = node;
            }
            current = node;
        }
        return head;
    }
}
