package com.shuangpeng.Problem.p0201_0300;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0226InvertBinaryTree {

    // 递归解法
    public TreeNode invertTree0(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree0(root.right);
        root.right = invertTree0(root.left);
        root.left = left;
        return root;
    }

    // 广度优先
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
        }
        return root;
    }
}
