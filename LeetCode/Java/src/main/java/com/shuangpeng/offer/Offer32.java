package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offer32 {

    public List<List<Integer>> levelOrder0(TreeNode root) {
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
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            answer.add(list);
        }
        return answer;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        dfs(root, answer, 0);
        return answer;
    }

    private void dfs(TreeNode treeNode, List<List<Integer>> answer, int depth) {
        if (treeNode == null) {
            return;
        }
        if (answer.size() <= depth) {
            List<Integer> list = new ArrayList<>();
            list.add(treeNode.val);
            answer.add(list);
        } else {
            answer.get(depth).add(treeNode.val);
        }
        dfs(treeNode.left, answer, depth + 1);
        dfs(treeNode.right, answer, depth + 1);
    }
}
