package com.shuangpeng;

import java.util.HashMap;
import java.util.Map;

public class Problem0236LowestCommonAncesterOfABinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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
}
