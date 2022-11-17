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
        return build(root, Integer.MIN_VALUE, nodeMap, visited) != Integer.MAX_VALUE && visited.isEmpty() ? root : null;
    }

    // 中序遍历
    private int build(TreeNode root, int minValue, Map<Integer, TreeNode> nodeMap, Set<Integer> visited) {
        if (root.val <= minValue) {
            return Integer.MAX_VALUE;
        }
        if (root.left != null) {
            if (nodeMap.containsKey(root.left.val)) {
                root.left = nodeMap.get(root.left.val);
            }
            if (build(root.left, minValue, nodeMap, visited) >= root.val) {
                return Integer.MAX_VALUE;
            }
        }
        visited.remove(root.val);
        int ans = root.val;
        if (root.right != null) {
            if (nodeMap.containsKey(root.right.val)) {
                root.right = nodeMap.get(root.right.val);
            }
            ans = build(root.right, root.val, nodeMap, visited);
        }
        return ans;
    }
}