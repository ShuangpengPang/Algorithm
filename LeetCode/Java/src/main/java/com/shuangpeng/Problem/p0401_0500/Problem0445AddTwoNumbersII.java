package com.shuangpeng.Problem.p0401_0500;

import com.shuangpeng.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0445AddTwoNumbersII（两数相加II）
 * @date 2023/7/3 12:05 PM
 */
public class Problem0445AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        for (ListNode node = l1; node != null; node = node.next) {
            list1.add(node.val);
        }
        for (ListNode node = l2; node != null; node = node.next) {
            list2.add(node.val);
        }
        List<Integer> list = new ArrayList<>();
        int carry = 0;
        for (int i = list1.size() - 1, j = list2.size() - 1; i >= 0 || j >= 0; i--, j--) {
            if (i >= 0) {
                carry += list1.get(i);
            }
            if (j >= 0) {
                carry += list2.get(j);
            }
            list.add(carry % 10);
            carry /= 10;
        }
        if (carry > 0) {
            list.add(carry);
        }
        ListNode dummy = new ListNode(), node = dummy;
        for (int i = list.size() - 1; i >= 0; i--, node = node.next) {
            node.next = new ListNode(list.get(i));
        }
        return dummy.next;
    }
}
