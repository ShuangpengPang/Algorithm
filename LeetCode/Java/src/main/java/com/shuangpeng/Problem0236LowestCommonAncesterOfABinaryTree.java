package com.shuangpeng;

public class Problem0236LowestCommonAncesterOfABinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findCommonAncestor(root, p, q);
    }

    public TreeNode findCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p) {
            if (checkNode(root.left, q) != null || checkNode(root.right, q) != null) {
                return root;
            } else {
                return null;
            }
        }
        if (root == q) {
            if (checkNode(root.left, p) != null || checkNode(root.right, p) != null) {
                return root;
            } else {
                return null;
            }
        }
        TreeNode pLeft = checkNode(root.left, p);
        TreeNode qRight = checkNode(root.right, q);
        if ((pLeft == null && qRight == null) || (pLeft != null &&qRight != null)) {
            return root;
        }
        if (pLeft != null) {
            return findCommonAncestor(root.left, p, q);
        }
        return findCommonAncestor(root.right, p, q);
    }

    public TreeNode checkNode(TreeNode root, TreeNode node) {
        if (root == null) {
            return null;
        }
        if (root == node) {
            return root;
        }
        if (root.left != null) {
            TreeNode left = checkNode(root.left, node);
            if (left != null) {
                return left;
            }
        }
        return checkNode(root.right, node);
    }
}
