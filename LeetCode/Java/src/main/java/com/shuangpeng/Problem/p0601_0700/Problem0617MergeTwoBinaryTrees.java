package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:（合并二叉树）
 * @date 2023/8/14 10:47 AM
 **/
public class Problem0617MergeTwoBinaryTrees {

    // 递归（深度优先）
    public TreeNode mergeTrees0(TreeNode t1, TreeNode t2) {
        return recurse(t1, t2);
    }

    public TreeNode recurse(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            t1 = t2;
            return t1;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = recurse(t1.left, t2.left);
        t1.right = recurse(t1.right, t2.right);
        return t1;
    }

    // 广度优先
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(t1);
        queue2.offer(t2);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            node1.val += node2.val;
            if (node1.left != null && node2.left != null) {
                queue1.offer(node1.left);
                queue2.offer(node2.left);
            } else if (node1.left == null) {
                node1.left = node2.left;
            }
            if (node1.right != null && node2.right != null) {
                queue1.offer(node1.right);
                queue2.offer(node2.right);
            } else if (node1.right == null) {
                node1.right = node2.right;
            }
        }
        return t1;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    private TreeNode dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = dfs(root1.left, root2.left);
        root1.right = dfs(root1.right, root2.right);
        return root1;
    }
}
