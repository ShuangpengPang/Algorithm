package com.shuangpeng.Problem.p1901_2000;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1932MergeBSTsToCreateSingleBST（合并多棵二叉搜索树）
 * @date 2022/11/16 7:34 PM
 */
public class Problem1932MergeBSTsToCreateSingleBST {

    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (TreeNode node : trees) {
            nodeMap.put(node.val, node);
            set.add(node.val);
        }
        Set<Integer> visited = new HashSet<>();
        for (TreeNode node : trees) {
            if (node.left != null) {
                int val = node.left.val;
                if (visited.contains(val)) {
                    return null;
                }
                if (set.remove(val)) {
                    visited.add(val);
                }
            }
            if (node.right != null) {
                int val = node.right.val;
                if (visited.contains(val)) {
                    return null;
                }
                if (set.remove(val)) {
                    visited.add(val);
                }
            }
        }
        if (set.size() != 1) {
            return null;
        }
        TreeNode root = nodeMap.get(set.iterator().next());
        int[] arr = build(root, nodeMap);
        if (arr == null || arr[0] != trees.size() - 1) {
            return null;
        }
        return root;
    }

    private int[] build(TreeNode root, Map<Integer, TreeNode> nodeMap) {
        int ans = 0;
        int min = root.left == null ? root.val : root.left.val;
        int max = root.right == null ? root.val : root.right.val;
        if (root.left != null && nodeMap.containsKey(root.left.val)) {
            TreeNode node = nodeMap.get(root.left.val);
            int[] arr = build(node, nodeMap);
            if (arr == null || arr[2] >= root.val) {
                return null;
            }
            root.left = node;
            min = arr[1];
            ans += arr[0] + 1;
        }
        if (root.right != null && nodeMap.containsKey(root.right.val)) {
            TreeNode node = nodeMap.get(root.right.val);
            int[] arr = build(node, nodeMap);
            if (arr == null || arr[1] <= root.val) {
                return null;
            }
            root.right = node;
            max = arr[2];
            ans += arr[0] + 1;
        }
        return new int[]{ans, min, max};
    }
}