package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

public class Problem0687LongestUnivaluePath {

    public int longestUnivaluePath0(TreeNode root) {
        return recurse(root);
    }

    private int recurse(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getPath(node.left, node.val) + getPath(node.right, node.val) + 2,
                Math.max(recurse(node.left), recurse(node.right)));
    }

    private int getPath(TreeNode node, int value) {
        if (node == null || node.val != value) {
            return -1;
        }
        return Math.max(getPath(node.left, value), getPath(node.right, value)) + 1;
    }

    int ans;
    public int longestUnivaluePath1(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }
    public int arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    private int answer = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return answer;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        int leftPath = 0, rightPath= 0;
        if (node.left != null && node.val == node.left.val) {
            leftPath = left + 1;
        }
        if (node.right != null && node.val == node.right.val) {
            rightPath = right + 1;
        }
        answer = Math.max(answer, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
}
