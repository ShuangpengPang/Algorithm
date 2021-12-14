package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0086PartitionList {

    public ListNode partition0(ListNode head, int x) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        int n = list.size();
        int l = 0, r = 0;
        while (l < n && r < n) {
            while (l < n && list.get(l) < x) {
                ++l;
            }
            r = l + 1;
            while (r < n && list.get(r) >= x) {
                ++r;
            }
            if (r < n) {
                int temp = list.get(r);
                for (int i = r; i >= l + 1; --i) {
                    list.set(i, list.get(i - 1));
                }
                list.set(l, temp);
            }
            ++l;
        }
        node = head;
        for (int i = 0; i < n; ++i) {
            node.val = list.get(i);
            node = node.next;
        }
        return head;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode smallDummy = new ListNode();
        ListNode largeDummy = new ListNode();
        ListNode small = smallDummy;
        ListNode large = largeDummy;
        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                small.next = node;
                small = small.next;
            } else {
                large.next = node;
                large = large.next;
            }
            node = node.next;
        }
        large.next = null;
        small.next = largeDummy.next;
        return smallDummy.next;
    }
}
