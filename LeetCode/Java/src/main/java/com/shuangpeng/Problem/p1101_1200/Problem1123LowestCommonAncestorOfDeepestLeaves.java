package com.shuangpeng.Problem.p1101_1200;

import com.shuangpeng.common.TreeNode;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:（最深叶节点的最近公共祖先）
 * @date 2023/9/6 12:14 PM
 **/
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

class Problem1123LowestCommonAncestorOfDeepestLeaves0 {

    private Map<Integer, Integer> depth;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        depth = new HashMap<>();
        dfs(root);
        return getNode(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int d = Math.max(dfs(root.left), dfs(root.right)) + 1;
        depth.put(root.val, d);
        return d;
    }

    private TreeNode getNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        int left = root.left == null ? 0 : depth.get(root.left.val);
        int right = root.right == null ? 0 : depth.get(root.right.val);
        if (left == right) {
            return root;
        }
        if (left > right) {
            return getNode(root.left);
        }
        return getNode(root.right);
    }
}

class Problem1123LowestCommonAncestorOfDeepestLeaves1 {

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).getValue();
    }

    private Pair<Integer, TreeNode> dfs(TreeNode root) {
        if (root == null) {
            return new Pair<>(0, null);
        }
        Pair<Integer, TreeNode> left = dfs(root.left), right = dfs(root.right);
        int leftDepth = left.getKey(), rightDepth = right.getKey();
        if (leftDepth > rightDepth) {
            return new Pair<>(leftDepth + 1, left.getValue());
        } else if (rightDepth > leftDepth) {
            return new Pair<>(rightDepth + 1, right.getValue());
        }
        return new Pair<>(leftDepth + 1, root);
    }
}

class Problem1123LowestCommonAncestorOfDeepestLeaves2 {

    TreeNode ans;
    int maxDepth;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        ans = root;
        maxDepth = 0;
        dfs(root, 0);
        return ans;
    }

    private int dfs(TreeNode root, int depth) {
        if (root == null) {
            maxDepth = Math.max(maxDepth, depth);
            return depth;
        }
        int left = dfs(root.left, depth + 1), right = dfs(root.right, depth + 1);
        if (left == right && left == maxDepth) {
            ans = root;
        }
        return Math.max(left, right);
    }
}
