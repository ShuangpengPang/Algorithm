package com.shuangpeng.interview;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0202KthToLast（返回倒数第K个节点）
 * @date 2024/5/24 11:32 AM
 */
public class Question0202KthToLast {

    public int kthToLast0(ListNode head, int k) {
        int cnt = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            cnt++;
        }
        cnt -= k;
        node = head;
        while (cnt > 0) {
            node = node.next;
            cnt--;
        }
        return node.val;
    }

    public int kthToLast(ListNode head, int k) {
        ListNode right = head;
        for (int i = 0; i < k; i++) {
            right = right.next;
        }
        ListNode left = head;
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        return left.val;
    }
}
