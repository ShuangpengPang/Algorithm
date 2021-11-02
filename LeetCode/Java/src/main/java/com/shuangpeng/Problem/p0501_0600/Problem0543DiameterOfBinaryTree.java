package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

public class Problem0543DiameterOfBinaryTree {

    int max = 0;
    public int diameterOfBinaryTree0(TreeNode root) {
        max = 0;
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        if (root.left == null) {
            return depth(root.right) + 1;
        }
        if (root.right == null) {
            return depth(root.left) + 1;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.max(max, left + right + 2);
    }

    public int depth(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        max = Math.max(max, left + right + 2);
        return Math.max(left, right) + 1;
    }

    int answer = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        answer = 0;
        maxNodeCount(root);
        return answer;
    }

    public int maxNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxNodeCount(root.left);
        int right = maxNodeCount(root.right);
        answer = Math.max(answer, left + right);
        return Math.max(left, right) + 1;
    }
}
