package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

public class Problem0671SecondMinimumNodeInABinaryTree {

    private long min = Long.MAX_VALUE;
    private long secondMin = Long.MAX_VALUE;

    public int findSecondMinimumValue0(TreeNode root) {
        dfs(root);
        return secondMin == Long.MAX_VALUE ? -1 : (int) secondMin;
    }

    private void dfs(TreeNode node) {
        if (node == null || node.val >= secondMin) {
            return;
        }
        if (node.val < min) {
            secondMin = min;
            min = node.val;
        } else if (node.val < secondMin && node.val != min) {
            secondMin = node.val;
        }
        dfs(node.left);
        dfs(node.right);
    }

    public int findSecondMinimumValue1(TreeNode root) {
        if (root.left == null) {
            return -1;
        }
        int leftValue = root.left.val;
        int rightValue = root.right.val;
        if (leftValue == rightValue) {
            leftValue = findSecondMinimumValue1(root.left);
            rightValue = findSecondMinimumValue1(root.right);
        } else if (leftValue > root.val) {
            rightValue = findSecondMinimumValue1(root.right);
        } else if (rightValue > root.val) {
            leftValue = findSecondMinimumValue1(root.left);
        }
        if (leftValue == -1) {
            return rightValue;
        }
        if (rightValue == -1) {
            return leftValue;
        }
        return Math.min(leftValue, rightValue);
    }

    public int findSecondMinimumValue(TreeNode root) {
        int[] p = new int[]{root.val, -1};
        dfs(root, p);
        return p[1];
    }

    private void dfs(TreeNode root, int[] p) {
        if (root == null) {
            return;
        }
        if (p[1] != -1 && root.val >= p[1]) {
            return;
        }
        if (root.val > p[0] && (p[1] == -1 || root.val < p[1])) {
            p[1] = root.val;
        }
        dfs(root.left, p);
        dfs(root.right, p);
    }
}
