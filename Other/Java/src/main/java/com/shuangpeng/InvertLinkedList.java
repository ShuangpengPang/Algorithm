package com.shuangpeng;

public class InvertLinkedList {

    class Node {
        int data;
        Node next;
    }

    // 递归
    public Node invertListRecursive(Node node) {
        if (node == null) {
            return null;
        }
        if (node.next == null) {
            Node head = new Node();
            head.next = node;
            return head;
        }

        Node newHead = invertListRecursive(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    // 迭代
    public Node invertList(Node node) {
        if (node == null) {
            return null;
        }
        Node previousNode = node;
        Node nextNode = node.next;
//        node.next = null;

        while (nextNode != null) {
            Node temp = nextNode.next;
            nextNode.next = previousNode;
            previousNode = nextNode;
            nextNode = temp;
        }

        Node head = new Node();
        head.next = previousNode;
        return head;
    }

    public Node insertList(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Node head = new Node();
        Node tail = head;
        for (int data : array) {
            Node node = new Node();
            node.data = data;
            tail.next = node;
            tail = node;
        }
        return head;
    }

    public void printList(Node head) {
        Node current = head;
        while (current != null) {
            if (current == head) {
                System.err.print(current.data);
            } else {
                System.err.print(", " + current.data);
            }
            current = current.next;
        }
        System.err.println();
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        InvertLinkedList invertLinkedList = new InvertLinkedList();
        Node head = invertLinkedList.insertList(array);
        invertLinkedList.printList(head.next);
//        Node newHead = invertLinkedList.invertListRecursive(head.next);
        Node newHead = invertLinkedList.invertList(head.next);
        invertLinkedList.printList(newHead.next);
    }
}
