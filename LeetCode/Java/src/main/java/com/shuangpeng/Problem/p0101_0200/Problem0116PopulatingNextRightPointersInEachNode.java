package com.shuangpeng.Problem.p0101_0200;

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

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start.left != null) {
            Node current = start;
            while (current != null) {
                current.left.next = current.right;
                if (current.next != null) {
                    current.right.next = current.next.left;
                }
                current = current.next;
            }
            start = start.left;
        }
        return root;
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // 从根节点开始
        Node leftmost = root;

        while (leftmost.left != null) {

            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // 指针向后移动
                head = head.next;
            }

            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }

        return root;
    }
}
