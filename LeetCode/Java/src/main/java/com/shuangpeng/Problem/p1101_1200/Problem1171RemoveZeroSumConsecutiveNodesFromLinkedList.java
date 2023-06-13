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

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        map.put(0, dummy);
        Deque<Integer> q = new ArrayDeque<>();
        q.push(0);
        int sum = 0;
        while (head != null) {
            sum += head.val;
            ListNode node = map.get(sum);
            if (node != null) {
                while (map.get(q.peek()) != node) {
                    map.remove(q.pop());
                }
                node.next = head.next;
            } else {
                map.put(sum, head);
                q.push(sum);
            }
            head = head.next;
        }
        return dummy.next;
    }
}
