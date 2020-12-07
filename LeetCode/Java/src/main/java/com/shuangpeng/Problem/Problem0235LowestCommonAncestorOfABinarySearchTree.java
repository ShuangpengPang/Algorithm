package com.shuangpeng.Problem;

public class Problem0235LowestCommonAncestorOfABinarySearchTree {

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

    TreeNode answer = null;
    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        answer = null;
        recurse(root, p, q);
        return answer;
    }

    public boolean recurse(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = recurse(root.left, p, q);
        boolean right = recurse(root.right, p, q);
        if (((root == p || root == q) && (left || right)) || (left && right)) {
            answer = root;
        }
        return root == p || root == q || left || right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        if (p.val > q.val) {
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        while (true) {
            if (p.val > root.val) {
                root = root.right;
            } else if (q.val < root.val) {
                root = root.left;
            } else {
                return root;
            }
        }
    }
}
