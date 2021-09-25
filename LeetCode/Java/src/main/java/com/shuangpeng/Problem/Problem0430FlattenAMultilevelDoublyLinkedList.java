package com.shuangpeng.Problem;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem0430FlattenAMultilevelDoublyLinkedList {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public Node flatten0(Node head) {
        Deque<Node> stack = new ArrayDeque<>();
        Node node = head;
        while (node != null) {
            if (node.child != null) {
                if (node.next != null) {
                    stack.offerLast(node.next);
                }
                Node child = node.child;
                node.child = null;
                node.next = child;
                child.prev = node;
                node = child;
            } else if (node.next != null) {
                node = node.next;
            } else if (!stack.isEmpty()) {
                Node top = stack.pollLast();
                node.next = top;
                top.prev = node;
                node = top;
            } else {
                break;
            }
        }
        return head;
    }

    public Node flatten(Node head) {
        for (Node node = head; node != null; node = node.next) {
            if (node.child != null) {
                Node temp = node.next;
                Node child = node.child;
                node.next = child;
                child.prev = node;
                node.child = null;
                Node last = child;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = temp;
                if (temp != null) {
                    temp.prev = last;
                }
            }
        }
        return head;
    }
}
