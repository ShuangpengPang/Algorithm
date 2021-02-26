package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Question0406InorderSuccessor {

    private boolean find = false;
    private TreeNode answer = null;

    public TreeNode inorderSuccessor0(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.right != null) {
            TreeNode leftMost = p.right;
            while (leftMost.left != null) {
                leftMost = leftMost.left;
            }
            return leftMost;
        }
        dfs(root, p);
        return answer;
    }

    private void dfs(TreeNode node, TreeNode p) {
        if (node == null || answer != null) {
            return;
        }
        dfs(node.left, p);
        if (node == p) {
            find = true;
        } else if (find && answer == null) {
            answer = node;
            return;
        }
        dfs(node.right, p);
    }

    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.right != null) {
            return getLeftMost(p.right);
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != p) {
            stack.offerLast(node);
            if (node.val < p.val) {
                node = node.right;
            } else if (node.val > p.val) {
                node = node.left;
            }
        }
        if (node != p) {
            return null;
        }
        TreeNode current = p;
        while (!stack.isEmpty()) {
            TreeNode parent = stack.removeLast();
            if (current == parent.left) {
                return parent;
            }
            current = parent;
        }
        return null;
    }

    private TreeNode getLeftMost(TreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode pre = null;
        while (root.val != p.val) {
            //右边
            if (p.val > root.val) {
                root = root.right;
            }
            //左边
            else {
                pre = root;
                root = root.left;
            }
        }
        //假如没有右子树
        if (root.right == null) {
            return pre;
        } else {
            root = root.right;
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.right != null) {
            TreeNode leftMost = p.right;
            while (leftMost.left != null) {
                leftMost = leftMost.left;
            }
            return leftMost;
        }
        TreeNode previous = null;
        TreeNode node = root;
        while (node != null && node != p) {
            if (node.val < p.val) {
                node = node.right;
            } else {
                previous = node;
                node = node.left;
            }
        }
        if (node != p) {
            return null;
        }
        return previous;
    }
}
