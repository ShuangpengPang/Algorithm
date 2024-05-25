package com.shuangpeng.interview;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0203DeleteNode（面试题 02.03 删除中间节点）
 * @date 2024/5/25 10:16 AM
 */
public class Question0203DeleteNode {

    // 错误做法，node是中间节点
//    public void deleteNode(ListNode node) {
//        if (node == null || node.next == null || node.next.next == null) {
//            return;
//        }
//        ListNode prev = null, slow = node, fast = node.next;
//        while (fast != null && fast.next != null) {
//            prev = slow;
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        prev.next = prev.next.next;
//    }

    public void deleteNode(ListNode node) {
        ListNode prev = null;
        while (node.next != null) {
            prev = node;
            node.val = node.next.val;
            node = node.next;
        }
        prev.next = null;
    }
}
