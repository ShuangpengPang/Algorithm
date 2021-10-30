package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.TreeNode;

public class Problem0112PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return recurse(root, targetSum);
    }

    private boolean recurse(TreeNode node, int targetSum) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return node.val == targetSum;
        }
        return recurse(node.left, targetSum - node.val)
                || recurse(node.right, targetSum - node.val);
    }
}
