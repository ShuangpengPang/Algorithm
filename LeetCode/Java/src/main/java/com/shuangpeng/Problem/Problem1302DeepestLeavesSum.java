package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem1302DeepestLeavesSum {

    private int answer = 0;
    private int maxDepth = 0;

    public int deepestLeavesSum0(TreeNode root) {
        dfs(root, 0);
        return answer;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        if (depth > maxDepth) {
            maxDepth = depth;
            answer = node.val;
        } else if (depth == maxDepth) {
            answer += node.val;
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int answer = 0;
        while (!queue.isEmpty()) {
            answer = 0;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                answer += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return answer;
    }
}
