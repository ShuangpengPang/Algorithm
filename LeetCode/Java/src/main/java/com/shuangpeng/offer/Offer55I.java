package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

public class Offer55I {

    public int maxDepth(TreeNode root) {
        return getDepth(root);
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getDepth(root.left);
        int rightHeight = getDepth(root.right);
        return leftHeight >= rightHeight ? leftHeight + 1 : rightHeight + 1;
    }
}
