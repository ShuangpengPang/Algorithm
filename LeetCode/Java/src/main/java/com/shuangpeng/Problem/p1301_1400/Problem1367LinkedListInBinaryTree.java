package com.shuangpeng.Problem.p1301_1400;

import com.shuangpeng.common.ListNode;
import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

class Problem1367LinkedListInBinaryTree0 {

    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfs(head, root);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return check(head, root) || dfs(head, root.left) || dfs(head, root.right);
    }

    private boolean check(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null || root.val != head.val) {
            return false;
        }
        return check(head.next, root.left) || check(head.next, root.right);
    }
}

class Problem1367LinkedListInBinaryTree1 {
    int n;
    boolean res = false;
    int[] next;

    public boolean isSubPath(ListNode head, TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        n = list.size();
        next = new int[n];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < n - 1) {
            //因为题目标明数字在1-100范围，所以可以用==不用equals
            if (j < 0 || list.get(i) == list.get(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        dfs(root, list, 0);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> list, int i) {
        if (res) {
            return;
        }
        if (i == n) {
            res = true;
            return;
        }
        if (node == null) {
            return;
        }
        if (i < 0 || node.val == list.get(i)) {
            dfs(node.left, list, i + 1);
            dfs(node.right, list, i + 1);
        } else {
            dfs(node, list, next[i]);
        }
    }
}
