package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

public class Question0404CheckBalance {

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) > -1;
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (leftHeight == -1 || rightHeight == -1
                || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
