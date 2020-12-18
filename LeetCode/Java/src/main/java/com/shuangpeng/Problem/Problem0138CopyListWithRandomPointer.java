package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.Map;

public class Problem0138CopyListWithRandomPointer {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList0(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Integer> nodeMap = new HashMap<>();
        Map<Integer, Node> indexMap = new HashMap<>();
        Map<Node, Integer> resultNodeMap = new HashMap<>();
        Map<Integer, Node> resultIndexMap = new HashMap<>();
        Node node = head;
        int[] p = {0};
        while (node != null) {
            Node newNode = getNode(node, nodeMap, indexMap, resultNodeMap, resultIndexMap, p);
            if (node.next != null) {
                newNode.next = getNode(node.next, nodeMap, indexMap, resultNodeMap, resultIndexMap, p);
            }
            if (node.random != null) {
                newNode.random = getNode(node.random, nodeMap, indexMap, resultNodeMap, resultIndexMap, p);
            }
            node = node.next;
        }
        return resultIndexMap.get(0);
    }

    public Node getNode(Node node, Map<Node, Integer> nodeMap, Map<Integer, Node> indexMap,
                        Map<Node, Integer> resultNodeMap, Map<Integer, Node> resultIndexMap, int[] p) {
        int count = p[0];
        if (!nodeMap.containsKey(node)) {
            nodeMap.put(node, count);
            indexMap.put(count, node);
            Node newNode = new Node(node.val);
            resultNodeMap.put(newNode, count);
            resultIndexMap.put(count, newNode);
            p[0]++;
        }
        return resultIndexMap.get(nodeMap.get(node));
    }

//    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();
//
//    public Node copyRandomList(Node head) {
//
//        if (head == null) {
//            return null;
//        }
//
//        if (this.visitedHash.containsKey(head)) {
//            return this.visitedHash.get(head);
//        }
//
//        Node node = new Node(head.val);
//
//        this.visitedHash.put(head, node);
//
//        node.next = this.copyRandomList(head.next);
//        node.random = this.copyRandomList(head.random);
//
//        return node;
//    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        while (node != null) {
            Node copy = getNode(node, map);
            if (node.next != null) {
                copy.next = getNode(node.next, map);
            }
            if (node.random != null) {
                copy.random = getNode(node.random, map);
            }
            node = node.next;
        }
        return map.get(head);
    }

    public Node getNode(Node node, Map<Node, Node> map) {
        if (!map.containsKey(node)) {
            Node newNode = new Node(node.val);
            map.put(node, newNode);
        }
        return map.get(node);
    }
}
