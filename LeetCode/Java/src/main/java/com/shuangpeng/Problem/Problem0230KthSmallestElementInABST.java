package com.shuangpeng.Problem;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Problem0230KthSmallestElementInABST {

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

    public int kthSmallest0(TreeNode root, int k) {
        if (root == null || k == 0) {
            return 0;
        }
        return innorderTraverse(root, k)[1];
    }

    public int[] innorderTraverse(TreeNode root, int k) {
        if (root == null) {
            return new int[]{0, Integer.MIN_VALUE};
        }
        int[] left = innorderTraverse(root.left, k);
        if (left[1] != Integer.MIN_VALUE) {
            return left;
        }
        if (k == left[0] + 1) {
            left[1] = root.val;
            return left;
        }
        int[] right = innorderTraverse(root.right, k - (left[0] + 1));
        return new int[]{left[0] + right[0] + 1, right[1]};
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k == 0) {
            return 0;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (true) {
            while (root != null) {
                stack.offer(root);
                root = root.left;
            }
            TreeNode node = stack.removeLast();
            if (--k == 0) {
                return node.val;
            }
            root = node.right;
        }
    }
}
