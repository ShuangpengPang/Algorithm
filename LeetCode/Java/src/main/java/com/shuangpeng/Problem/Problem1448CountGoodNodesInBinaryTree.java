package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem1448CountGoodNodesInBinaryTree {

    private int answer = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return answer;
    }

    private void dfs(TreeNode node, int max) {
        if (node == null) {
            return;
        }
        if (node.val >= max) {
            answer++;
            max = node.val;
        }
        dfs(node.left, max);
        dfs(node.right, max);
    }
}
