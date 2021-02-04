package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Offer68 {

    private TreeNode answer;

    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return answer;
    }

    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        TreeNode left = dfs(node.left, p, q);
        TreeNode right = dfs(node.right, p, q);
        if ((node.val == p.val || node.val == q.val)
                && (left != null || right != null)) {
            answer = node;
            return null;
        }
        if (left != null && right != null) {
            answer = node;
            return null;
        }
        if (node.val == p.val || node.val == q.val) {
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

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path_p = getPath(root, p);
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<TreeNode>();
        TreeNode node = root;
        while (node != target) {
            path.add(node);
            if (target.val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        TreeNode node = root;
        while (node != null) {
            int value = node.val;
            if (p.val < value && q.val < value) {
                node = node.left;
            } else if (p.val > value && q.val > value) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }
}
