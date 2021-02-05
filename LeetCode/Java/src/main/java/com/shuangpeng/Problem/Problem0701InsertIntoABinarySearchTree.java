package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0701InsertIntoABinarySearchTree {

    public TreeNode insertIntoBST0(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        root = recurse(root, node);
        return root;
    }

    private TreeNode recurse(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }
        if (node.val < root.val) {
            root.left = recurse(root.left, node);
        } else {
            root.right = recurse(root.right, node);
        }
        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            return node;
        }
        TreeNode parent = root;
        TreeNode current = root;
        while (current != null) {
            parent = current;
            if (val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (val < parent.val) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        return root;
    }
}
