package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

public class Question0405ValidBST {

    public boolean isValidBST0(TreeNode root) {
        return recurse(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean recurse(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return recurse(root.left, min, root.val) && recurse(root.right, root.val, max);
    }

    private long value = Long.MIN_VALUE;

    public boolean isValidBST1(TreeNode root) {
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {
            return false;
        }
        if (root.val <= value) {
            return false;
        }
        value = root.val;
        return inorder(root.right);
    }

    public boolean isValidBST(TreeNode root) {
        long value = Long.MIN_VALUE;
        while (root != null) {
            TreeNode node = root.left;
            if (node != null) {
                while (node.right != null && node.right != root) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = root;
                    root = root.left;
                } else if (value < root.val) {
                    node.right = null;
                    value = root.val;
                    root = root.right;
                } else {
                    node.right = null;
                    return false;
                }
            } else if (root.val > value) {
                value = root.val;
                root = root.right;
            }
        }
        return true;
    }
}
