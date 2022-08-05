package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: 在二叉树中增加一行
 * @Date 2022/8/5 12:14 PM
 **/
public class Problem0623AddOneRowToTree {

    public TreeNode addOneRow0(TreeNode root, int v, int d) {
        if (root == null) {
            return null;
        }
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        List<TreeNode> list = new ArrayList<>();
        dfs(root, 1, d, list);
        for (TreeNode node : list) {
            TreeNode newLeft = new TreeNode(v);
            TreeNode newRight = new TreeNode(v);
            newLeft.left = node.left;
            newRight.right = node.right;
            node.left = newLeft;
            node.right = newRight;
        }
        return root;
    }

    private void dfs(TreeNode node, int depth, int d, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        if (depth + 1 == d) {
            list.add(node);
            return;
        }
        dfs(node.left, depth + 1, d, list);
        dfs(node.right, depth + 1, d, list);
    }

    public TreeNode addOneRow1(TreeNode root, int v, int d) {
        if (root == null) {
            return null;
        }
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        insert(root, 1, v, d);
        return root;
    }

    private void insert(TreeNode node, int depth, int v, int d) {
        if (node == null) {
            return;
        }
        if (depth + 1 == d) {
            TreeNode newLeft = new TreeNode(v);
            TreeNode newRight = new TreeNode(v);
            newLeft.left = node.left;
            newRight.right = node.right;
            node.left = newLeft;
            node.right = newRight;
        } else {
            insert(node.left, depth + 1, v, d);
            insert(node.right, depth + 1, v, d);
        }
    }

    public TreeNode addOneRow2(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int d = 1;
        while (d < depth - 1) {
            ++d;
            for (int i = q.size() - 1; i >= 0; i--) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            TreeNode left = new TreeNode(val), right = new TreeNode(val);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
        }
        return root;
    }

    public TreeNode addOneRow3(TreeNode root, int val, int depth) {
        return dfs(root, val, depth);
    }

    private TreeNode dfs(TreeNode root, int val, int depth) {
        if (root == null) {
            return null;
        }
        if (depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        if (depth == 2) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        } else {
            root.left = dfs(root.left, val, depth - 1);
            root.right = dfs(root.right, val, depth - 1);
        }
        return root;
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        List<TreeNode> cur = new ArrayList<>();
        cur.add(root);
        for (int d = 1; d < depth - 1; d++) {
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode node : cur) {
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            cur = tmp;
        }
        for (TreeNode node : cur) {
            node.left = new TreeNode(val, node.left, null);
            node.right = new TreeNode(val, null, node.right);
        }
        return root;
    }
}

