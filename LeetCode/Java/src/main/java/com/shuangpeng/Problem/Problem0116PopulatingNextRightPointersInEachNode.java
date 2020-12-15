package com.shuangpeng.Problem;

import com.shuangpeng.common.Node;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0116PopulatingNextRightPointersInEachNode {

    public Node connect0(Node root) {
        if (root == null) {
            return null;
        }
        recurse(root.left, root, true);
        recurse(root.right, null, false);
        return root;
    }

    public void recurse(Node node, Node elder, boolean isLeft) {
        if (node == null) {
            return;
        }
        if (elder != null) {
            if (isLeft) {
                node.next = elder.right;
            } else {
                node.next = elder.left;
            }
        }
        recurse(node.left, node, true);
        recurse(node.right, node.next, false);
    }

    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start.left != null) {
            Node current = start;
            current.left.next = current.right;
            Node previous = current;
            current = current.next;
            while (current != null) {
                previous.right.next = current.left;
                current.left.next = current.right;
                previous = current;
                current = current.next;
            }
            start = start.left;
        }
        return root;
    }
}
