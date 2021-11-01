package com.shuangpeng.Problem.p0401_0500;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0404SumOfLeftLeaves {

    private int answer = 0;

    public int sumOfLeftLeaves0(TreeNode root) {
        dfs(null, root);
        return answer;
    }

    private void dfs(TreeNode parent, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (parent != null && parent.left == node) {
                answer += node.val;
            }
            return;
        }
        if (node.left != null) {
            dfs(node, node.left);
        }
        if (node.right != null) {
            dfs(node, node.right);
        }
    }

    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        return recurse(root);
    }

    private int recurse(TreeNode node) {
        if (isLeafNode(node)) {
            return node.val;
        }
        int sum = node.left != null ? recurse(node.left) : 0;
        sum += node.right != null && !isLeafNode(node.right) ? recurse(node.right) : 0;
        return sum;
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        int sum = 0;
        if (node.left != null) {
            sum += isLeaf(node.left) ? node.left.val : dfs(node.left);
        }
        if (node.right != null) {
            sum += !isLeaf(node.right) ? dfs(node.right) : 0;
        }
        return sum;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null && isLeaf(node.left)) {
                sum += node.left.val;
            } else if (node.left != null && !isLeaf(node.left)) {
                queue.offer(node.left);
            }
            if (node.right != null && !isLeaf(node.right)) {
                queue.offer(node.right);
            }
        }
        return sum;
    }
}
