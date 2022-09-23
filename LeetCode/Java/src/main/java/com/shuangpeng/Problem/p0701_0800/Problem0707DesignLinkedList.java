package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0707DesignLinkedList（设计链表）
 * @Date 2022/9/23 10:04 AM
 * @Version 1.0
 */
public class Problem0707DesignLinkedList {
}

class MyLinkedList {

    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    Node head, tail;

    public MyLinkedList() {
        head = tail = null;
    }

    public int get(int index) {
        Node node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node == null ? -1 : node.val;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = tail = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        if (tail == null) {
            head = tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        Node node = head;
        for (int i = 0; i < index - 1 && node != null; i++) {
            node = node.next;
        }
        if (node != null) {
            if (node.next == null) {
                addAtTail(val);
            } else {
                Node n = new Node(val);
                n.next = node.next;
                node.next = n;
            }
        }
    }

    public void deleteAtIndex(int index) {
        if (head == null) {
            return;
        }
        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }
        Node node = head;
        for (int i = 0; i < index - 1 && node != null; i++) {
            node = node.next;
        }
        if (node != null) {
            if (node.next == null) {
                return;
            }
            node.next = node.next.next;
            if (node.next == null) {
                tail = node;
            }
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */