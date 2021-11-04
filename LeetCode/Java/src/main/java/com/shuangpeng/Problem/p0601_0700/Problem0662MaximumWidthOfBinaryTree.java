package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0662MaximumWidthOfBinaryTree {

    private long maxWidth = 0;

    public int widthOfBinaryTree0(TreeNode root) {
        dfs(root, 0, 0, 0, new ArrayList<>());
        return (int) maxWidth;
    }

    private void dfs(TreeNode node, int depth, long parent, int value, List<List<Long>> lists) {
        if (node == null) {
            return;
        }
        long code = (parent << 1) + value;
        if (depth >= lists.size()) {
            List<Long> list = new ArrayList<>(2);
            list.add(code);
            list.add(code);
            lists.add(list);
        }
        List<Long> list = lists.get(depth);
        if (code < list.get(0)) {
            list.set(0, code);
        }
        if (code > list.get(1)) {
            list.set(1, code);
        }
        long width = list.get(1) - list.get(0) + 1;
        maxWidth = Math.max(maxWidth, width);
        dfs(node.left, depth + 1, code, 0, lists);
        dfs(node.right, depth + 1, code, 1, lists);
    }

    private long answer = 0;

    public int widthOfBinaryTree1(TreeNode root) {
        dfs(root, 0, 0, new HashMap<>());
        return (int) answer;
    }

    private void dfs(TreeNode node, int depth, long position, Map<Integer, Long> map) {
        if (node == null) {
            return;
        }
        map.computeIfAbsent(depth, k -> position);
        long width = position - map.get(depth) + 1;
        answer = Math.max(answer, width);
        dfs(node.left, depth + 1, position << 1, map);
        dfs(node.right, depth + 1, (position << 1) + 1, map);
    }

    private int ans = 0;

    public int widthOfBinaryTree2(TreeNode root) {
        dfs(root, 0, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode node, int depth, int position, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (depth >= list.size()) {
            list.add(position);
        }
        int width = position - list.get(depth) + 1;
        ans = Math.max(ans, width);
        dfs(node.left, depth + 1, position << 1, list);
        dfs(node.right, depth + 1, (position << 1) + 1, list);
    }

    class AnnotatedNode {
        TreeNode node;
        int position;

        public AnnotatedNode(TreeNode node, int position) {
            this.node = node;
            this.position = position;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<AnnotatedNode> queue = new LinkedList<>();
        queue.offer(new AnnotatedNode(root, 0));
        int result = 0;
        while (!queue.isEmpty()) {
            result = Math.max(result, queue.peekLast().position - queue.peekFirst().position + 1);
            for (int i = queue.size(); i > 0; i--) {
                AnnotatedNode annotatedNode = queue.pollFirst();
                TreeNode node = annotatedNode.node;
                if (node.left != null) {
                    queue.offerLast(new AnnotatedNode(node.left, annotatedNode.position << 1));
                }
                if (node.right != null) {
                    queue.offerLast(new AnnotatedNode(node.right, (annotatedNode.position << 1) + 1));
                }
            }
        }
        return result;
    }
}
