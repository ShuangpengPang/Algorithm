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
        return dfs(root, 0, limit) < limit ? null : root;
    }

    private int dfs(TreeNode root, int sum, int limit) {
        if (root == null) {
            return Integer.MIN_VALUE >> 1;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        int left = dfs(root.left, sum, limit), right = dfs(root.right, sum, limit);
        root.left = left < limit ? null : root.left;
        root.right = right < limit ? null : root.right;
        return Math.max(left, right);
    }
}
