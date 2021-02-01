package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Offer28 {

    public boolean isSymmetric0(TreeNode root) {
        return recurse(root, root);
    }

    private boolean recurse(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return recurse(root1.left, root2.right) && recurse(root1.right, root2.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        Queue<TreeNode> leftQueue = new LinkedList<>();
        Queue<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.offer(root.left);
        rightQueue.offer(root.right);
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            TreeNode left = leftQueue.poll();
            TreeNode right = rightQueue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            leftQueue.offer(left.right);
            leftQueue.offer(left.left);
            rightQueue.offer(right.left);
            rightQueue.offer(right.right);
        }
        return leftQueue.isEmpty() && rightQueue.isEmpty();
    }
}
