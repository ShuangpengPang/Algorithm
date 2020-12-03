package com.shuangpeng.Problem;

public class Problem0124BinaryTreeMaximumPathSum {

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

    int maxSum = 0;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxSum = root.val;
        getNodeMaxSum(root);
        return maxSum;
    }

    public int getNodeMaxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int val = root.val;
        int left = Math.max(getNodeMaxSum(root.left), 0);
        int right = Math.max(getNodeMaxSum(root.right), 0);
        maxSum = Math.max(maxSum, val + left + right);
        return Math.max(val + left, val + right);
    }
}
