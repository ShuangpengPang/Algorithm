package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Question0408LowestCommonAncestor {

    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        TreeNode left = dfs(node.left, p, q);
        TreeNode right = dfs(node.right, p, q);
        if (node == p || node == q) {
            return node;
        }
        if (left != null && right != null) {
            return node;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            parent.put(node.left.val, node);
            dfs(node.left);
        }
        if (node.right != null) {
            parent.put(node.right.val, node);
            dfs(node.right);
        }
    }
}
