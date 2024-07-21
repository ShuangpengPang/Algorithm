package com.shuangpeng.Problem.p3201_3300;

import com.shuangpeng.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3217DeleteNodesFromLinkedListPresentInArray（从链表中移除在数组中存在的节点）
 * @date 2024/7/21 9:03 PM
 */
public class Problem3217DeleteNodesFromLinkedListPresentInArray {

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ListNode dummy = new ListNode(0, head), node = dummy;
        while (node.next != null) {
            if (set.contains(node.next.val)) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return dummy.next;
    }
}
