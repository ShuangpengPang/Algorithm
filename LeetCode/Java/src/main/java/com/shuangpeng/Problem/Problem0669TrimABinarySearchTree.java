package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0669TrimABinarySearchTree {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    private TreeNode dfs(TreeNode node, int low, int high) {
        if (node == null) {
            return null;
        }
        if (node.val < low) {
            return dfs(node.right, low, high);
        }
        if (node.val > high) {
            return dfs(node.left, low, high);
        }
        node.left = dfs(node.left, low, high);
        node.right = dfs(node.right, low, high);
        return node;
    }
}
