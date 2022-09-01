package com.shuangpeng.competition.第241到250场周赛.第249场周赛;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem1932 {

//    public TreeNode canMerge(List<TreeNode> trees) {
//        Map<Integer, TreeNode> map = new HashMap<>();
//        int n = trees.size();
//        for (int i = 0; i < n; i++) {
//            TreeNode node = trees.get(i);
//            map.put(node.val, node);
//        }
//
//    }
//
//    private boolean merge(TreeNode root, TreeNode node) {
//        TreeNode[] treeNodes = find(root, node);
//        if (treeNodes == null) {
//            return false;
//        }
//        TreeNode parent = treeNodes[0];
//        TreeNode child = treeNodes[1];
//        if (child == parent.left) {
//            if (getMax(node) >= parent.val) {
//                return false;
//            }
//            parent.left = node;
//            return true;
//        }
//        if (getMin(node) <= parent.val) {
//            return false;
//        }
//        parent.right = node;
//        return true;
//    }
//
//    private int getMax(TreeNode node) {
//        while (node.right != null) {
//            node = node.right;
//        }
//        return node.val;
//    }
//
//    private int getMin(TreeNode node) {
//        while (node.left != null) {
//            node = node.left;
//        }
//        return node.val;
//    }
//
//    private TreeNode[] find(TreeNode root, TreeNode node) {
//        if (node.val < root.val) {
//            if (root.left == null) {
//                return null;
//            }
//            if (root.left.val == node.val) {
//                return new TreeNode[]{root, root.left};
//            }
//            return find(root.left, node);
//        }
//        if (root.right == null) {
//            return null;
//        }
//        if (root.right.val == node.val) {
//            return new TreeNode[]{root, root.right};
//        }
//        return find(root.right, node);
//    }
}
