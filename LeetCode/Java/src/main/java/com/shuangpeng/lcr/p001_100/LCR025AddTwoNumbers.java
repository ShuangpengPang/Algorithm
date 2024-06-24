package com.shuangpeng.lcr.p001_100;

import com.shuangpeng.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR025AddTwoNumbers（两数相加II）
 * @date 2024/6/17 5:10 PM
 */
public class LCR025AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> list1 = getList(l1), list2 = getList(l2);
        if (list1.size() < list2.size()) {
            List<Integer> tmp = list1;
            list1 = list2;
            list2 = tmp;
        }
        int n1 = list1.size(), n2 = list2.size(), c = 0;
        for (int i = 0; i < n1; i++) {
            c += list1.get(i) + (i < n2 ? list2.get(i) : 0);
            list1.set(i, c % 10);
            c /= 10;
        }
        if (c > 0) {
            list1.add(c);
        }
        ListNode dummy = new ListNode(), prev = dummy;
        for (int i = list1.size() - 1; i >= 0; i--) {
            ListNode node = new ListNode(list1.get(i));
            prev.next = node;
            prev = node;
        }
        return dummy.next;
    }

    private List<Integer> getList(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            int tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
        return list;
    }
}

class LCR025AddTwoNumbers0 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = reverse(l1), node2 = reverse(l2);
        int carry = 0;
        ListNode ans = null;
        while (node1 != null || node2 != null || carry != 0) {
            if (node1 != null) {
                carry += node1.val;
                node1 = node1.next;
            }
            if (node2 != null) {
                carry += node2.val;
                node2 = node2.next;
            }
            ListNode node = new ListNode(carry % 10);
            node.next = ans;
            ans = node;
            carry /= 10;
        }
        return ans;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}
