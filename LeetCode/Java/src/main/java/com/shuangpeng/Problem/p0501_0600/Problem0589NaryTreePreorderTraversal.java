package com.shuangpeng.Problem.p0501_0600;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Problem0589NaryTreePreorderTraversal {

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

    public List<Integer> preorder0(Node root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        for (Node node : root.children) {
            dfs(node, list);
        }
    }

    public List<Integer> preorder1(Node root) {
        List<Integer> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        Deque<Integer> indexStack = new LinkedList<>();
        Deque<Node> nodeStack = new LinkedList<>();
        nodeStack.offerLast(root);
        indexStack.offerLast(0);
        answer.add(root.val);
        while (!nodeStack.isEmpty()) {
            int nextIndex = indexStack.peekLast();
            Node node = nodeStack.peekLast();
            int size = node.children.size();
            if (nextIndex < size) {
                Node nextNode = node.children.get(nextIndex);
                indexStack.pollLast();
                indexStack.offerLast(nextIndex + 1);
                answer.add(nextNode.val);
                nodeStack.offerLast(nextNode);
                indexStack.offerLast(0);
            } else {
                nodeStack.pollLast();
                indexStack.pollLast();
            }
        }
        return answer;
    }

    public List<Integer> preorder(Node root) {
        List<Integer> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.offer(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            answer.add(node.val);
            int size = node.children.size();
            for (int i = size - 1; i >= 0; i--) {
                stack.offer(node.children.get(i));
            }
        }
        return answer;
    }
}
