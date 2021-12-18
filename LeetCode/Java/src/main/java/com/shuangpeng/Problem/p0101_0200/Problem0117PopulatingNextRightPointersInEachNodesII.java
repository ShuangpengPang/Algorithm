package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.Node;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0117PopulatingNextRightPointersInEachNodesII {

    public Node connect0(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node prev = null;
            for (int i = queue.size() - 1; i >= 0; --i) {
                if (prev != null) {
                    prev.next = queue.peek();
                }
                prev = queue.poll();
                if (prev.left != null) {
                    queue.offer(prev.left);
                }
                if (prev.right != null) {
                    queue.offer(prev.right);
                }
            }
        }
        return root;
    }

    public Node connect1(Node root) {
        dfs(root);
        return root;
    }

    private void dfs(Node root) {
        if (root == null) {
            return;
        }
        dfs(buildList(root, null));
    }

    private Node buildList(Node root, Node prev) {
        if (root == null) {
            return prev;
        }
        Node temp = prev;
        if (root.left != null) {
            if (prev != null) {
                prev.next = root.left;
            }
            prev = root.left;
        }
        if (root.right != null) {
            if (prev != null) {
                prev.next = root.right;
            }
            prev = root.right;
        }
        Node next = buildList(root.next, prev);
        return temp != null ? temp : (root.left != null ? root.left : (root.right != null ? root.right : next));
    }

    public Node connect(Node root) {
        Node node = root;
        while (node != null) {
            Node head = null, prev = null;
            while (node != null) {
                if (node.left != null) {
                    if (prev == null) {
                        head = node.left;
                    } else {
                        prev.next = node.left;
                    }
                    prev = node.left;
                }
                if (node.right != null) {
                    if (prev == null) {
                        head = node.right;
                    } else {
                        prev.next = node.right;
                    }
                    prev = node.right;
                }
                node = node.next;
            }
            node = head;
        }
        return root;
    }
}
