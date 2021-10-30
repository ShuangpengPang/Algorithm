package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.TreeNode;

public class Problem0110BalancedBinaryTree {

    public boolean isBalanced0(TreeNode root) {
        return checkBalanced(root).isBalanced;
    }

    class NodeInfo {
        boolean isBalanced;
        int height;

        public NodeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    private NodeInfo checkBalanced(TreeNode root) {
        if (root == null) {
            return new NodeInfo(true, 0);
        }
        NodeInfo left = checkBalanced(root.left);
        if (!left.isBalanced) {
            return new NodeInfo(false, 1 + left.height);
        }
        NodeInfo right = checkBalanced(root.right);
        if (!right.isBalanced) {
            return new NodeInfo(false, 1 + Math.max(left.height, right.height));
        }
        boolean isBalanced = Math.abs(left.height - right.height) <= 1;
        return new NodeInfo(isBalanced, 1 + Math.max(left.height, right.height));
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = height(root.right);
        if (rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
