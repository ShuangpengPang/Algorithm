package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0958CheckCompletenessOfABinaryTree {

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (flag && (node.left != null || node.right != null)) {
                return false;
            }
            if (node.left == null && node.right != null) {
                return false;
            }
            if (!flag && (node.left == null || node.right == null)) {
                flag = true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return true;
    }
}
