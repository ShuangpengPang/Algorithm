package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0863AllNodesDistanceKInBinaryTree {

    public List<Integer> distanceK0(TreeNode root, TreeNode target, int K) {
        if (root == null || target == null || K < 0) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(root.val, new ArrayList<>());
        buildGraph(root, graph);
        List<Integer> answer = new LinkedList<>();
        dfs(target.val, -1, K, graph, answer);
        return answer;
    }

    private void dfs(int u, int f, int K, Map<Integer, List<Integer>> graph, List<Integer> answer) {
        if (K == 0) {
            answer.add(u);
            return;
        }
        for (int v : graph.get(u)) {
            if (v != f) {
                dfs(v, u, K - 1, graph, answer);
            }
        }
    }


    private void buildGraph(TreeNode node, Map<Integer, List<Integer>> graph) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            addEdge(node.val, node.left.val, graph);
            addEdge(node.left.val, node.val, graph);
        }
        if (node.right != null) {
            addEdge(node.val, node.right.val, graph);
            addEdge(node.right.val, node.val, graph);
        }
        buildGraph(node.left, graph);
        buildGraph(node.right, graph);
    }

    private void addEdge(int key, int value, Map<Integer, List<Integer>> graph) {
        if (!graph.containsKey(key)) {
            graph.put(key, new ArrayList<>());
        }
        graph.get(key).add(value);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        dfs(root, null, parent);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        queue.offer(null);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        int distance = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                if (node.left != null && !visited.contains(node.left)) {
                    queue.offer(node.left);
                    visited.add(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    queue.offer(node.right);
                    visited.add(node.right);
                }
                TreeNode parentNode = parent.get(node);
                if (parentNode != null && !visited.contains(parentNode)) {
                    queue.offer(parentNode);
                    visited.add(parentNode);
                }
            } else {
                distance++;
                if (distance == K) {
                    List<Integer> answer = new ArrayList<>();
                    for (TreeNode treeNode : queue) {
                        answer.add(treeNode.val);
                    }
                    return answer;
                }
                queue.offer(null);
            }
        }
        return new ArrayList<>();
    }

    private void dfs(TreeNode node, TreeNode parentNode, Map<TreeNode, TreeNode> parent) {
        if (node == null) {
            return;
        }
        parent.put(node, parentNode);
        dfs(node.left, node, parent);
        dfs(node.right, node, parent);
    }
}
