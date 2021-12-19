package com.shuangpeng.Problem.p0101_0200;

import java.util.*;

public class Problem0133CloneGraph {

    private class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph0(Node node) {
        if (node == null) {
            return null;
        }
        return dfs(node, new HashMap<>());
    }

    private Node dfs(Node node, Map<Integer, Node> map) {
        Node copyNode = new Node(node.val);
        List<Node> copyNeighbors = new ArrayList<>();
        copyNode.neighbors = copyNeighbors;
        map.put(node.val, copyNode);
        for (Node neighbor : node.neighbors) {
            if (!map.containsKey(neighbor.val)) {
                dfs(neighbor, map);
            }
            copyNeighbors.add(map.get(neighbor.val));
        }
        return copyNode;
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> visited = new HashMap<>();
        queue.offer(node);
        visited.put(node.val, new Node(node.val));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            Node copy = visited.get(curr.val);
            for (Node neighbor : curr.neighbors) {
                if (!visited.containsKey(neighbor.val)) {
                    queue.offer(neighbor);
                    Node copyNeighbor = new Node(neighbor.val);
                    visited.put(neighbor.val, copyNeighbor);
                }
                copy.neighbors.add(visited.get(neighbor.val));
            }
        }
        return visited.get(node.val);
    }
}
