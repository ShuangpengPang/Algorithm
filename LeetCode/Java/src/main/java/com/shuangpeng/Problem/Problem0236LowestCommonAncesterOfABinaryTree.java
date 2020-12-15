package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0236LowestCommonAncesterOfABinaryTree {

    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        Map<String, TreeNode> map = new HashMap<>();
        return findCommonAncestor(root, p, q, map);
    }

    public TreeNode findCommonAncestor(TreeNode root, TreeNode p, TreeNode q, Map<String, TreeNode> map) {
        if (root == p) {
            if (checkNode(root.left, q, map) != null || checkNode(root.right, q, map) != null) {
                return root;
            } else {
                return null;
            }
        }
        if (root == q) {
            if (checkNode(root.left, p, map) != null || checkNode(root.right, p, map) != null) {
                return root;
            } else {
                return null;
            }
        }
        TreeNode pLeft = checkNode(root.left, p, map);
        TreeNode qRight = checkNode(root.right, q, map);
        if ((pLeft == null && qRight == null) || (pLeft != null &&qRight != null)) {
            return root;
        }
        if (pLeft != null) {
            return findCommonAncestor(root.left, p, q, map);
        }
        return findCommonAncestor(root.right, p, q, map);
    }

    public TreeNode checkNode(TreeNode root, TreeNode node, Map<String, TreeNode> map) {
        if (root == null) {
            return null;
        }
        if (root == node) {
            return root;
        }
        String key = "" + root.val + "-" + node.val;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (root.left != null) {
            TreeNode left = checkNode(root.left, node, map);
            if (left != null) {
                map.put(key, left);
                return left;
            }
        }
        TreeNode right = checkNode(root.right, node, map);
        map.put(key, right);
        return right;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return result;
    }

    TreeNode result;
    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);
        if ((left && right) || ((root == p || root == q) && (left || right))) {
            result = root;
        }
        return left || right || root == p || root == q;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                map.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                map.put(node.right, node);
                queue.offer(node.right);
            }
        }
        Set<TreeNode> visited = new HashSet<>();
        visited.add(p);
        TreeNode parent = map.get(p);
        while (parent != null) {
            visited.add(parent);
            parent = map.get(parent);
        }
        TreeNode current = q;
        while (!visited.contains(current)) {
            current = map.get(current);
        }
        return current;
    }
}
