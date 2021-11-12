package com.shuangpeng.Problem.p1301_1400;

import com.shuangpeng.common.TreeNode;

public class Problem1325DeleteLeavesWithAGivenValue {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return dfs(root, target);
    }

    private TreeNode dfs(TreeNode node, int target) {
//        if (node == null
//                || (node.val == target && node.left == null && node.right == null)) {
//            return null;
//        }
        if (node == null) {
            return null;
        }
        node.left = dfs(node.left, target);
        node.right = dfs(node.right, target);
        return node.val == target && node.left == null && node.right == null ? null : node;
    }
}
