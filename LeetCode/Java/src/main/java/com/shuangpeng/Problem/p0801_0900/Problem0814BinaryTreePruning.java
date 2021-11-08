package com.shuangpeng.Problem.p0801_0900;

import com.shuangpeng.common.TreeNode;

public class Problem0814BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        return recurse(root);
    }

    private TreeNode recurse(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = recurse(root.left);
        root.right = recurse(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }
}
