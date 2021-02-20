package com.shuangpeng.Problem;

import java.util.List;

public class Problem0559MaximumDepthOfNAryTree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {
        return dfs(root);
    }

    private int dfs(Node node) {
        if (node == null) {
            return 0;
        }
        int depth = 0;
        for (Node child : node.children) {
            depth = Math.max(depth, dfs(child));
        }
        return depth + 1;
    }
}
