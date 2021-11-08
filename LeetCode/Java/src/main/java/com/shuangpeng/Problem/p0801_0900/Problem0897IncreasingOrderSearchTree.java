package com.shuangpeng.Problem.p0801_0900;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0897IncreasingOrderSearchTree {

    public TreeNode increasingBST0(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recurse(root, list);
        TreeNode dummy = new TreeNode();
        TreeNode current = dummy;
        for (int value : list) {
            TreeNode treeNode = new TreeNode(value);
            current.right = treeNode;
            current = treeNode;
        }
        return dummy.right;
    }

    private void recurse(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        recurse(treeNode.left, list);
        list.add(treeNode.val);
        recurse(treeNode.right, list);
    }

    private TreeNode current;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode();
        current = dummy;
        inorder(root);
        return dummy.right;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        root.left = null;
        current.right = root;
        current = root;
        inorder(root.right);
    }
}
