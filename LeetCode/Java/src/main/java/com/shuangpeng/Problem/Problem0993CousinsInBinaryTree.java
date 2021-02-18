package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0993CousinsInBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        Problem0993CousinsInBinaryTree a = new Problem0993CousinsInBinaryTree();
        a.isCousins(root, 4, 5);
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.val == x || root.val == y) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            boolean hasX = false;
            boolean hasY = false;
            TreeNode parentX = null;
            TreeNode parentY = null;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    if (node.left.val == x) {
                        hasX = true;
                        parentX = node;
                    } else if (node.left.val == y) {
                        hasY = true;
                        parentY = node;
                    }
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    if (node.right.val == x) {
                        hasX = true;
                        parentX = node;
                    } else if (node.right.val == y) {
                        hasY = true;
                        parentY = node;
                    }
                }
            }
            if (hasX ^ hasY || (parentX != null && parentX == parentY)) {
                return false;
            }
            if (hasX && hasY) {
                return true;
            }
        }
        return false;
    }
}
