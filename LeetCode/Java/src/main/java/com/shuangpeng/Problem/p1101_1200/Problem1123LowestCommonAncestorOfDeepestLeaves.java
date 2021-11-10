package com.shuangpeng.Problem.p1101_1200;

import com.shuangpeng.common.TreeNode;

public class Problem1123LowestCommonAncestorOfDeepestLeaves {

    class TreeNodeInfo {
        TreeNode node;
        int height;

        TreeNodeInfo(TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }

    public TreeNode lcaDeepestLeaves0(TreeNode root) {
        return dfs(root).node;
    }

    private TreeNodeInfo dfs(TreeNode root) {
        if (root == null) {
            return new TreeNodeInfo(null, -1);
        }
        TreeNodeInfo leftInfo = dfs(root.left);
        TreeNodeInfo rightInfo = dfs(root.right);
        if (leftInfo.height == rightInfo.height) {
            return new TreeNodeInfo(root, leftInfo.height + 1);
        }
        if (leftInfo.height > rightInfo.height) {
            leftInfo.height++;
            return leftInfo;
        }
        rightInfo.height++;
        return rightInfo;
    }

    TreeNode res = null;
    int pre = 0;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 1);
        return res;

    }

    int dfs(TreeNode node, int depth) {
        if (node == null) return depth;
        int left = dfs(node.left, depth + 1);
        int right = dfs(node.right, depth + 1);
        if (left == right && left >= pre) {
            res = node;
            pre = left;
        }
        return Math.max(left, right);
    }
}
