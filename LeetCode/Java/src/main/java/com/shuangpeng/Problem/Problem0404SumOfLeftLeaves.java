package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0404SumOfLeftLeaves {

    private int answer = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        dfs(null, root);
        return answer;
    }

    private void dfs(TreeNode parent, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (parent != null && parent.left == node) {
                answer += node.val;
            }
            return;
        }
        if (node.left != null) {
            dfs(node, node.left);
        }
        if (node.right != null) {
            dfs(node, node.right);
        }
    }
}
