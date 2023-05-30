package com.shuangpeng.Problem.p1101_1200;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 删点成林
 * @date 2023/5/30 12:16 PM
 **/
public class Problem1110DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null || to_delete == null || to_delete.length == 0) {
            return new ArrayList<TreeNode>() {{
                add(root);
            }};
        }
        Set<Integer> deleteSet = new HashSet<>();
        for (int value : to_delete) {
            deleteSet.add(value);
        }
        List<TreeNode> answer = new ArrayList<>();
        dfs(root, deleteSet, answer, false);
        return answer;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> deleteSet, List<TreeNode> answer, boolean hasParent) {
        boolean toDelete = deleteSet.contains(node.val);
        if (!hasParent && !toDelete) {
            answer.add(node);
        }
        if (node.left != null) {
            node.left = dfs(node.left, deleteSet, answer, !toDelete);
        }
        if (node.right != null) {
            node.right = dfs(node.right, deleteSet, answer, !toDelete);
        }
        if (toDelete) {
            return null;
        }
        return node;
    }
}

class Problem1110DeleteNodesAndReturnForest0 {

    List<TreeNode> ans;
    Set<Integer> set;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        ans = new ArrayList<>();
        set = new HashSet<>(to_delete.length);
        for (int val : to_delete) {
            set.add(val);
        }
        TreeNode node = dfs(root);
        if (node != null) {
            ans.add(node);
        }
        return ans;
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = dfs(root.left);
        root.right = dfs(root.right);
        if (set.contains(root.val)) {
            if (root.left != null) {
                ans.add(root.left);
            }
            if (root.right != null) {
                ans.add(root.right);
            }
            return null;
        }
        return root;
    }
}

class Problem1110DeleteNodesAndReturnForest2 {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>(to_delete.length);
        for (int val : to_delete) {
            set.add(val);
        }
        List<TreeNode> ans = new ArrayList<>();
        dfs(root, true, set, ans);
        return ans;
    }

    private TreeNode dfs(TreeNode root, boolean isRoot, Set<Integer> set, List<TreeNode> ans) {
        if (root == null) {
            return null;
        }
        boolean toDelete = set.contains(root.val);
        if (isRoot && !toDelete) {
            ans.add(root);
        }
        root.left = dfs(root.left, toDelete, set, ans);
        root.right = dfs(root.right, toDelete, set, ans);
        return toDelete ? null : root;
    }
}
