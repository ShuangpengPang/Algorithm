package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.TreeNode;

public class Problem0124BinaryTreeMaximumPathSum {

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
