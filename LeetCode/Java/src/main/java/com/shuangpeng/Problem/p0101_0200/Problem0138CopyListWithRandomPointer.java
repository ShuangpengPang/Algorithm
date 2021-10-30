package com.shuangpeng.Problem.p0101_0200;

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

    public Node copyRandomList1(Node head) {
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

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node current = head;
        while (current != null) {
            Node node = new Node(current.val);
            node.next = current.next;
            current.next = node;
            current = node.next;
        }
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
        Node cloneHead = head.next;
        current = head;
        while (current.next.next != null) {
            Node clone = current.next;
            current.next = clone.next;
            clone.next = clone.next.next;
            current = current.next;
        }
        current.next = null;
        return cloneHead;
    }

    public Node copyRandomList3(Node head) {

        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {

            // Cloned node
            Node newNode = new Node(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }

    public Node copyRandomList4(Node head) {
        Map<Node, Integer> indexMap = new HashMap<>();
        Map<Integer, Node> nodeMap = new HashMap<>();
        int i = 0;
        Node node = head;
        Node dummy = new Node(0);
        Node copyNode = dummy;
        while (node != null) {
            Node copy = new Node(node.val);
            indexMap.put(node, i);
            nodeMap.put(i, copy);
            i++;
            copyNode.next = copy;
            node = node.next;
            copyNode = copy;
        }
        node = head;
        copyNode = dummy.next;
        while (node != null) {
            if (node.random != null) {
                int index = indexMap.get(node.random);
                copyNode.random = nodeMap.get(index);
            }
            node = node.next;
            copyNode = copyNode.next;
        }
        return dummy.next;
    }

    public Node copyRandomList(Node head) {
        Node node = head;
        while (node != null) {
            Node copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
            node = copy.next;
        }
        node = head;
        while (node != null) {
            Node copy = node.next;
            if (node.random != null) {
                copy.random = node.random.next;
            }
            node = copy.next;
        }
        Node dummy = new Node(0);
        node = head;
        Node previous = dummy;
        while (node != null) {
            previous.next = node.next;
            previous = node.next;
            node.next = previous.next;
            node = node.next;
        }
        return dummy.next;
    }
}
