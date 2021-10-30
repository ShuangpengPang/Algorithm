package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0103BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder0(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (reverse) {
                    list.addFirst(node.val);
                } else {
                    list.addLast(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            answer.add(list);
            reverse = !reverse;
        }
        return answer;
    }

//    [3,9,20,null,null,15,7]

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List answer = new ArrayList<>();
        dfs(root, 0, answer);
        return answer;
    }

    public void dfs(TreeNode node, int level, List<LinkedList<Integer>> answer) {
        if (node == null) {
            return;
        }
        if (level >= answer.size()) {
            LinkedList<Integer> list = new LinkedList<>();
            answer.add(list);
        }
        if (level % 2 == 0) {
            answer.get(level).addLast(node.val);
        } else {
            answer.get(level).addFirst(node.val);
        }
        if (node.left != null) {
            dfs(node.left, level + 1, answer);
        }
        if (node.right != null) {
            dfs(node.right, level + 1, answer);
        }
    }
}
