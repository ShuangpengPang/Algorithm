package com.shuangpeng.Problem;

import java.util.*;

public class Problem0590NAryTreePostorderTraversal {

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
    }

    public List<Integer> postorder0(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> answer = new ArrayList<>();
        dfs(root, answer);
        return answer;
    }

    private void dfs(Node node, List<Integer> answer) {
        for (Node child : node.children) {
            dfs(child, answer);
        }
        answer.add(node.val);
    }

    public List<Integer> postorder1(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> answer = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            Node node = stack.peekLast();
            if (!visited.contains(node)) {
                visited.add(node);
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.offerLast(node.children.get(i));
                }
            } else {
                answer.add(node.val);
                stack.pollLast();
                visited.remove(node);
            }
        }
        return answer;
    }

    public List<Integer> postorder2(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.add(item);
                }
            }
        }
        return output;
    }

    public List<Integer> postorder(Node root) {
        List<Integer> answer = new LinkedList<>();
        if (root == null) {
            return answer;
        }
        Deque<Node> stack = new LinkedList<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            answer.add(0, node.val);
            for (Node child : node.children) {
                stack.offerLast(child);
            }
        }
        return answer;
    }















}
