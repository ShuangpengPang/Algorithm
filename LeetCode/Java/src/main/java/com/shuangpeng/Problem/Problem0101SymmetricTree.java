package com.shuangpeng.Problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Problem0101SymmetricTree {

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

    // 递归
    public boolean isSymmetricRecursively(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return isSymmetricRecursively(node1.left, node2.right) && isSymmetricRecursively(node1.right, node2.left);
    }

    public boolean isSymmetric0(TreeNode root) {
//        return isSymmetricRecursively(root, root);
        return isSymmetricIteratively(root);
    }

    public boolean isSymmetricIteratively(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int half = size / 2;
            TreeNode[] stack = new TreeNode[half];
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                if (i < half) {
                    stack[count++] = node;
                } else {
                    if (count <= 0) {
                        return false;
                    }
                    TreeNode top = stack[--count];
                    if (top == null && node == null) {
                        continue;
                    } else if (top == null || node == null) {
                        return false;
                    } else if (top.val != node.val) {
                        return false;
                    }
                }
            }
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i = i + 2) {
                TreeNode left = queue.poll();
                TreeNode right = queue.poll();
                if (left == null && right == null) {
                    continue;
                }
                if (left == null || right == null) {
                    return false;
                }
                if (left.val != right.val) {
                    return false;
                }
                queue.offer(left.left);
                queue.offer(right.right);
                queue.offer(left.right);
                queue.offer(right.left);
            }
        }
        return true;
    }

}
