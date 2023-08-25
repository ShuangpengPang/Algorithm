package com.shuangpeng.Problem.p1401_1500;

import com.shuangpeng.common.TreeNode;

/**
 * @description:（统计二叉树中好节点的数目）
 * @date 2023/8/25 10:42 AM
 **/
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

class Problem1448CountGoodNodesInBinaryTree0 {

    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        if (max <= root.val) {
            ans = 1;
            max = root.val;
        }
        return ans + dfs(root.left, max) + dfs(root.right, max);
    }
}
