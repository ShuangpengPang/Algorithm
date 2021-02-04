package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0107BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom0(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        dfs(root, 0, answer);
        Collections.reverse(answer);
        return answer;
    }

    private void dfs(TreeNode node, int level, List<List<Integer>> answer) {
        if (node == null) {
            return;
        }
        if (level >= answer.size()) {
            answer.add(new ArrayList<>());
        }
        answer.get(level).add(node.val);
        dfs(node.left, level + 1, answer);
        dfs(node.right, level + 1, answer);
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            answer.add(list);
        }
        Collections.reverse(answer);
        return answer;
    }
}
