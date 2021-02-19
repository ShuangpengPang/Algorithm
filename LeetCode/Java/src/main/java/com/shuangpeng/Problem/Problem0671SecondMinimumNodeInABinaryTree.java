package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0671SecondMinimumNodeInABinaryTree {

    private long min = Long.MAX_VALUE;
    private long secondMin = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
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
}
