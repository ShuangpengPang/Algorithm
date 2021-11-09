package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

public class Problem0998MaximumBinaryTreeII {

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        return getTreeNode(root, val, true);
    }

    private TreeNode getTreeNode(TreeNode node, int val, boolean check) {
        if (node == null && !check) {
            return null;
        }
        if (node == null) {
            return new TreeNode(val);
        }
        if (!check) {
            TreeNode treeNode = new TreeNode(node.val);
            treeNode.left = getTreeNode(node.left, val, check);
            treeNode.right = getTreeNode(node.right, val, check);
            return treeNode;
        }
        TreeNode treeNode = null;
        if (node.val <= val) {
            treeNode = new TreeNode(val);
            treeNode.left = getTreeNode(node, val, false);
            treeNode.right = null;
        } else if (node.val > val) {
            treeNode = new TreeNode(node.val);
            treeNode.left = getTreeNode(node.left, val, false);
            treeNode.right = getTreeNode(node.right, val, true);
        }
        return treeNode;
    }
}
