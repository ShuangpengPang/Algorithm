package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Offer27 {

    public TreeNode mirrorTree0(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        dfs(node.left);
        dfs(node.right);
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
