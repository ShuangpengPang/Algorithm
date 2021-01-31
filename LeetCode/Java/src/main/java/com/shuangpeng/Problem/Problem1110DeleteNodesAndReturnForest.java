package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
