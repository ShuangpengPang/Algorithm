package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

    public TreeNode addOneRow(TreeNode root, int v, int d) {
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
}
