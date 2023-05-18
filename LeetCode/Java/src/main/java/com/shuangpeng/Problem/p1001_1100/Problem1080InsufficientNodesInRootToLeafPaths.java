package com.shuangpeng.Problem.p1001_1100;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1080InsufficientNodesInRootToLeafPaths（根到叶路径上的不足节点）
 * @date 2023/5/18 11:45 PM
 */
public class Problem1080InsufficientNodesInRootToLeafPaths {

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (dfs(root, 0, limit) < limit) {
            return null;
        }
        return root;
    }

    private int dfs(TreeNode root, int sum, int limit) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        int left = dfs(root.left, sum, limit);
        int right = dfs(root.right, sum, limit);
        if (left < limit) {
            root.left = null;
        }
        if (right < limit) {
            root.right = null;
        }
        return Math.max(left, right);
    }
}
