package com.shuangpeng.interview;

import com.shuangpeng.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0201RemoveDuplicateNodes（面试题 02.01 移除重复节点）
 * @date 2024/5/24 11:50 AM
 */
public class Question0201RemoveDuplicateNodes {

    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        Set<Integer> set = new HashSet<>();
        ListNode prev = dummy;
        for (ListNode node = head; node != null; node = node.next) {
            if (set.add(node.val)) {
                prev.next = node;
                prev = node;
            }
        }
        prev.next = null;
        return dummy.next;
    }
}
