package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0104MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
