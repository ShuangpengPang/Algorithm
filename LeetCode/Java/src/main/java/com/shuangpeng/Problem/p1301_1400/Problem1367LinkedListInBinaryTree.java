package com.shuangpeng.Problem.p1301_1400;

import com.shuangpeng.common.ListNode;
import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1367LinkedListInBinaryTree（二叉树中的链表）
 * @date 2023/8/9 3:32 PM
 */
public class Problem1367LinkedListInBinaryTree {

    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfs(head, root, true);
    }

    private boolean dfs(ListNode head, TreeNode root, boolean isHead) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (head.val == root.val && (dfs(head.next, root.left, false) || dfs(head.next, root.right, false))) {
            return true;
        }
        return isHead ? dfs(head, root.left, isHead) || dfs(head, root.right, isHead) : false;
    }
}
