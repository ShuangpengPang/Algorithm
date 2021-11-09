package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

public class Problem0965UnivaluedBinaryTree {

    public boolean isUnivalTree0(TreeNode root) {
        return dfs(root);
    }

    private boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return true;
        }
        if (node.left == null) {
            return node.val == node.right.val && dfs(node.right);
        }
        if (node.right == null) {
            return node.val == node.left.val && dfs(node.left);
        }
        return node.val == node.left.val && node.val == node.right.val
                && dfs(node.left) && dfs(node.right);
    }

    public boolean isUnivalTree(TreeNode root) {
        return recurse(root);
    }

    private boolean recurse(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (node.left != null && (node.val != node.left.val || !recurse(node.left))) {
            return false;
        }
        if (node.right != null && (node.val != node.right.val || !recurse(node.right))) {
            return false;
        }
        return true;
    }
}
