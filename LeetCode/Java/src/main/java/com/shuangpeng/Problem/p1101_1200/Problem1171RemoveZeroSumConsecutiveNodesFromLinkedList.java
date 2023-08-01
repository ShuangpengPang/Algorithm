package com.shuangpeng.Problem.p1101_1200;

import com.shuangpeng.common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1171RemoveZeroSumConsecutiveNodesFromLinkedList（从链表中删去总和值为零的连续节点）
 * @date 2023/6/13 4:44 PM
 */
public class Problem1171RemoveZeroSumConsecutiveNodesFromLinkedList {

    public ListNode removeZeroSumSublists0(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        Map<Integer, ListNode> map = new HashMap<>();
        Deque<Integer> q = new ArrayDeque<>();
        q.push(0);
        map.put(0, dummy);
        int sum = 0;
        for (ListNode node = head; node != null; node = node.next) {
            sum += node.val;
            if (map.containsKey(sum)) {
                while (q.peek() != sum) {
                    map.remove(q.pop());
                }
                map.get(sum).next = node.next;
            } else {
                map.put(sum, node);
                q.push(sum);
            }
        }
        return dummy.next;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(0, head);
        int sum = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            map.put(sum, node);
        }
        sum = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            node.next = map.get(sum).next;
        }
        return dummy.next;
    }
}
