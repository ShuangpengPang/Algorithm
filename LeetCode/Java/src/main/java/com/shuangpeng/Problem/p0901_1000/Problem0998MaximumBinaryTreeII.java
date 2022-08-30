package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

/**
 * @Description:（最大二叉树II）
 * @Date 2022/8/30 10:26 AM
 **/
public class Problem0998MaximumBinaryTreeII {

    public TreeNode insertIntoMaxTree0(TreeNode root, int val) {
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

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (val > root.val) {
            node.left = root;
            return node;
        }
        TreeNode cur = root;
        while (cur.right != null && cur.right.val > val) {
            cur = cur.right;
        }
        node.left = cur.right;
        cur.right = node;
        return root;
    }
}
