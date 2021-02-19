package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0783MinimumDistanceBetweenBSTNodes {

    private int previous = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        preorder(root);
        return min;
    }

    private void preorder(TreeNode node) {
        if (node == null) {
            return;
        }
        preorder(node.left);
        if (previous != Integer.MIN_VALUE) {
            min = Math.min(min, node.val - previous);
        }
        previous = node.val;
        preorder(node.right);
    }
}
