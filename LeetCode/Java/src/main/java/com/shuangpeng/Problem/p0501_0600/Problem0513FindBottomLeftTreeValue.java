package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

public class Problem0513FindBottomLeftTreeValue {

    private int answer = 0;
    private int maxDepth = 0;

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 1);
        return answer;
    }

    private void dfs(TreeNode node, int depth) {
        if (node != null) {
            if (node.left == null && node.right == null && depth > maxDepth) {
                answer = node.val;
                maxDepth = depth;
                return;
            }
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }
}
