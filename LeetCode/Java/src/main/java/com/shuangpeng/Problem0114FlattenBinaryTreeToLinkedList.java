package com.shuangpeng;

public class Problem0114FlattenBinaryTreeToLinkedList {

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

    // 栈
    public void flatten0(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode[] stack = new TreeNode[100];
        int count = 0;
        TreeNode current = root;
        while (current.left != null || current.right != null || count > 0) {
            if (current.right != null) {
                stack[count++] = current.right;
            }
            if (current.left == null) {
                current.right = stack[--count];
            } else {
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }

    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        while (root != null) {
            if (root.left != null) {
                TreeNode current = root.left;
                while (current.right != null) {
                    current = current.right;
                }
                current.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode[] stack = new TreeNode[100];
        int count = 0;
        stack[count++] = root;
        TreeNode previous = null;
        while (count > 0) {
            TreeNode node = stack[--count];
            if (previous != null) {
                previous.left = null;
                previous.right = node;
            }
            if (node.right != null) {
                stack[count++] = node.right;
            }
            if (node.left != null) {
                stack[count++] = node.left;
            }
            previous = node;
        }
    }
}
