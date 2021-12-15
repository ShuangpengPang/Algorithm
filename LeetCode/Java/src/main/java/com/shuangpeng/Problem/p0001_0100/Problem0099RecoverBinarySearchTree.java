package com.shuangpeng.Problem.p0001_0100;

import com.shuangpeng.common.TreeNode;

public class Problem0099RecoverBinarySearchTree {

    public void recoverTree(TreeNode root) {
        TreeNode a = findLargeNode(root);
        TreeNode b = findSmallNode(root);
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    private TreeNode findLargeNode(TreeNode root) {
        TreeNode pre = null, cur = root;
        TreeNode ans = null;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = cur;
                    cur = cur.left;
                } else {
                    node.right = null;
                    if (pre != null && ans == null && pre.val > cur.val) {
                        ans = pre;
                    }
                    pre = cur;
                    cur = cur.right;
                }
            } else {
                if (pre != null && ans == null && pre.val > cur.val) {
                    ans = pre;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return ans;
    }

    private TreeNode findSmallNode(TreeNode root) {
        TreeNode pre = null;
        TreeNode cur = root;
        TreeNode ans = null;
        while (cur != null) {
            if (cur.right != null) {
                TreeNode node = cur.right;
                while (node.left != null && node.left != cur) {
                    node = node.left;
                }
                if (node.left == null) {
                    node.left = cur;
                    cur = cur.right;
                } else {
                    node.left = null;
                    if (pre != null && ans == null && pre.val < cur.val) {
                        ans = pre;
                    }
                    pre = cur;
                    cur = cur.left;
                }
            } else {
                if (pre != null && ans == null && pre.val < cur.val) {
                    ans = pre;
                }
                pre = cur;
                cur = cur.left;
            }
        }
        return ans;
    }
}
