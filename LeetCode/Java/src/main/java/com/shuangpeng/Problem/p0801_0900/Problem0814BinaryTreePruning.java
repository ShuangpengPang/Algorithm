package com.shuangpeng.Problem.p0801_0900;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: （二叉树剪枝）
 * @Date 2022/7/21 10:41 AM
 **/
public class Problem0814BinaryTreePruning {

    public TreeNode pruneTree0(TreeNode root) {
        return recurse(root);
    }

    private TreeNode recurse(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = recurse(root.left);
        root.right = recurse(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    public TreeNode pruneTree(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode node = root, rightNode = null;
        while (node != null || !q.isEmpty()) {
            while (node != null) {
                q.push(node);
                node = node.left;
            }
            node = q.peek().right;
            if (node == null || node == rightNode) {
                node = q.pop();
                if (q.isEmpty()) {
                    if (node.val == 0 && node.left == null && node.right == null) {
                        return null;
                    } else {
                        return node;
                    }
                } else {
                    TreeNode res = node.val == 0 && node.left == null && node.right == null ? null : node;
                    if (node == q.peek().left) {
                        q.peek().left = res;
                        node = q.peek().right;
                        rightNode = node;
                    } else {
                        q.peek().right = res;
                        rightNode = res;
                        node = null;
                    }
                }
            } else {
                rightNode = node;
            }
        }
        return null;
    }
}
