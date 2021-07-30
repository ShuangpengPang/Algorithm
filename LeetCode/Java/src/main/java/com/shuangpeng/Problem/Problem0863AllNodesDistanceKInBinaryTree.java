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

    public List<Integer> distanceK1(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        dfs(root, null, parent);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(null);
        queue.offer(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);

        visited.add(null);

        int distance = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
//                if (node.left != null && !visited.contains(node.left)) {
//                    queue.offer(node.left);
//                    visited.add(node.left);
//                }
//                if (node.right != null && !visited.contains(node.right)) {
//                    queue.offer(node.right);
//                    visited.add(node.right);
//                }
//                TreeNode parentNode = parent.get(node);
//                if (parentNode != null && !visited.contains(parentNode)) {
//                    queue.offer(parentNode);
//                    visited.add(parentNode);
//                }

                if (!visited.contains(node.left)) {
                    queue.offer(node.left);
                    visited.add(node.left);
                }
                if (!visited.contains(node.right)) {
                    queue.offer(node.right);
                    visited.add(node.right);
                }
                TreeNode parentNode = parent.get(node);
                if (!visited.contains(parentNode)) {
                    queue.offer(parentNode);
                    visited.add(parentNode);
                }
            } else {
                if (distance == K) {
                    List<Integer> answer = new ArrayList<>();
                    for (TreeNode treeNode : queue) {
                        answer.add(treeNode.val);
                    }
                    return answer;
                }
                queue.offer(null);
                distance++;
            }
        }
        return new ArrayList<>();
    }

    private void dfs(TreeNode node, TreeNode parentNode, Map<TreeNode, TreeNode> parent) {
//        if (node == null) {
//            return;
//        }
//        parent.put(node, parentNode);
//        dfs(node.left, node, parent);
//        dfs(node.right, node, parent);

        if (node != null) {
            parent.put(node, parentNode);
            dfs(node.left, node, parent);
            dfs(node.right, node, parent);
        }
    }


    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        List<Integer> answer = new ArrayList<>();
        dfs(root, target, K, answer);
        return answer;
    }

    private int dfs(TreeNode node, TreeNode target, int K, List<Integer> answer) {
        if (node == null) {
            return -1;
        }
        if (node == target) {
            search(node, 0, K, answer);
            return 0;
        } else {
            int left = dfs(node.left, target, K, answer);
            int right = dfs(node.right, target, K, answer);
            if (left != -1) {
                int distance = left + 1;
                if (distance == K) {
                    answer.add(node.val);
                }
                if (distance < K) {
                    search(node.right, distance + 1, K, answer);
                }
                return distance;
            } else if (right != -1) {
                int distance = right + 1;
                if (distance == K) {
                    answer.add(node.val);
                }
                if (distance < K) {
                    search(node.left, distance + 1, K, answer);
                }
                return distance;
            } else {
                return -1;
            }
        }
    }

    private void search(TreeNode node, int distance, int K, List<Integer> answer) {
        if (node == null) {
            return;
        }
        if (distance == K) {
            answer.add(node.val);
        } else {
            search(node.left, distance + 1, K, answer);
            search(node.right, distance + 1, K, answer);
        }
    }

    class Solution {
        List<Integer> ans;
        TreeNode target;
        int K;
        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            ans = new LinkedList();
            this.target = target;
            this.K = K;
            dfs(root);
            return ans;
        }

        // Return vertex distance from node to target if exists, else -1
        // Vertex distance: the number of vertices on the path from node to target
        public int dfs(TreeNode node) {
            if (node == null)
                return -1;
            else if (node == target) {
                subtree_add(node, 0);
                return 1;
            } else {
                int L = dfs(node.left), R = dfs(node.right);
                if (L != -1) {
                    if (L == K) ans.add(node.val);
                    subtree_add(node.right, L + 1);
                    return L + 1;
                } else if (R != -1) {
                    if (R == K) ans.add(node.val);
                    subtree_add(node.left, R + 1);
                    return R + 1;
                } else {
                    return -1;
                }
            }
        }

        // Add all nodes 'K - dist' from the node to answer.
        public void subtree_add(TreeNode node, int dist) {
            if (node == null) return;
            if (dist == K)
                ans.add(node.val);
            else {
                subtree_add(node.left, dist + 1);
                subtree_add(node.right, dist + 1);
            }
        }
    }

    public List<Integer> distanceK3(TreeNode root, TreeNode target, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, target, -1, map);
        List<Integer> answer = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key).equals(k)) {
                answer.add(key);
            }
        }
        return answer;
    }

    private int dfs(TreeNode root, TreeNode target, int distance, Map<Integer, Integer> map) {
        if (root == null) {
            return distance;
        }
        if (distance != -1) {
            distance++;
            map.put(root.val, distance);
        }
        if (root == target) {
            distance = 0;
            map.put(root.val, 0);
        }
        int leftDistance = dfs(root.left, target, distance, map);
        int rightDistance = dfs(root.right, target, distance, map);
        if (distance != -1 || (leftDistance == -1 && rightDistance == -1)) {
            return distance;
        }
        if (leftDistance != -1) {
            distance = leftDistance + 1;
            dfs(root.right, target, distance, map);
        } else {
            distance = rightDistance + 1;
            dfs(root.left, target, distance, map);
        }
        map.put(root.val, distance);
        return distance;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, TreeNode> parents = new HashMap<>();
        findParent(root, parents);
        List<Integer> answer = new ArrayList<>();
        findNodes(target, 0, k, null, parents, answer);
        return answer;
    }

    private void findParent(TreeNode root, Map<Integer, TreeNode> parents) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parents.put(root.left.val, root);
            findParent(root.left, parents);
        }
        if (root.right != null) {
            parents.put(root.right.val, root);
            findParent(root.right, parents);
        }
    }

    private void findNodes(TreeNode root, int distance, int k, TreeNode from,
                           Map<Integer, TreeNode> parents, List<Integer> answer) {
        if (root == null) {
            return;
        }
        if (distance == k) {
            answer.add(root.val);
            return;
        }
        if (root.left != from) {
            findNodes(root.left, distance + 1, k, root, parents, answer);
        }
        if (root.right != from) {
            findNodes(root.right, distance + 1, k, root, parents, answer);
        }
        if (parents.get(root.val) != from) {
            findNodes(parents.get(root.val), distance + 1, k, root, parents, answer);
        }
    }

//    public static void main(String[] args) {
//        Problem0863AllNodesDistanceKInBinaryTree a = new Problem0863AllNodesDistanceKInBinaryTree();
//        TreeNode root = new TreeNode(0);
//        root.left = new TreeNode(1);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);
//        a.distanceK(root, root.left.right, 1);
//    }
}
