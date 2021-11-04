package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

public class Problem0700SearchInABinarySearchTree {

    public TreeNode searchBST0(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null) {
            if (node.val < val) {
                node = node.right;
            } else if (node.val > val) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }
}
