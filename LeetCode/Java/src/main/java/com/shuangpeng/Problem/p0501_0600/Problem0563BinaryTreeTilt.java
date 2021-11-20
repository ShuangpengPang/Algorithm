package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

public class Problem0563BinaryTreeTilt {

    public int findTilt(TreeNode root) {
        int[] ans = {0};
        dfs(root, ans);
        return ans[0];
    }

    private int dfs(TreeNode root, int[] p) {
        if (root == null) {
            return 0;
        }
        int leftSum = dfs(root.left, p);
        int rightSum = dfs(root.right, p);
        p[0] += Math.abs(leftSum - rightSum);
        return leftSum + rightSum + root.val;
    }
}
