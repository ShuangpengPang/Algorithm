package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0707DesignLinkedList（设计链表）
 * @Date 2022/9/23 10:04 AM
 * @Version 1.0
 */
public class Problem0707DesignLinkedList {

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
}

class Problem0707DesignLinkedList0 {

    class MyLinkedList {

        class Node {
            int val;
            Node next;
            Node(int val) {
                this.val = val;
            }
        }

        Node head, tail;
        int size;

        public MyLinkedList() {
            size = 0;
            head = tail = new Node(0);
        }

        public int get(int index) {
            if (index >= size) {
                return -1;
            }
            Node node = head.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.val;
        }

        public void addAtHead(int val) {
            Node node = new Node(val);
            node.next = head.next;
            head.next = node;
            if (size == 0) {
                tail = node;
            }
            size++;
        }

        public void addAtTail(int val) {
            Node node = new Node(val);
            tail.next = node;
            tail = node;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index == size) {
                addAtTail(val);
                return;
            }
            Node node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            Node cur = new Node(val);
            cur.next = node.next;
            node.next = cur;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (size == 0 || index >= size) {
                return;
            }
            Node node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            if (node.next == null) {
                tail = node;
            }
            size--;
        }
    }
}

class Problem0707DesignLinkedList1 {

    class MyLinkedList {

        class Node {
            int val;
            Node prev, next;
            Node(int val) {
                this.val = val;
            }
        }

        Node head;
        int size;

        public MyLinkedList() {
            size = 0;
            head = new Node(0);
            head.next = head;
            head.prev = head;
        }

        public int get(int index) {
            if (index >= size || index < 0) {
                return -1;
            }
            return getNode(index).val;
        }

        public void addAtHead(int val) {
            addNode(head, val);
        }

        public void addAtTail(int val) {
            Node node = size == 0 ? head : getNode(size - 1);
            addNode(node, val);
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) {
                return;
            }
            Node node = index == 0 ? head : getNode(index - 1);
            addNode(node, val);
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            Node node = index == 0 ? head : getNode(index - 1);
            node.next.next.prev = node;
            node.next = node.next.next;
            size--;
        }

        public Node getNode(int index) {
            if (index < 0 || index >= size) {
                return null;
            }
            if (index < size >> 1) {
                Node node = head.next;
                for (int i = 0; i < index; i++) {
                    node = node.next;
                }
                return node;
            }
            Node node = head;
            int cnt = size - index;
            for (int i = 0; i < cnt; i++) {
                node = node.prev;
            }
            return node;
        }

        private void addNode(Node prev, int val) {
            Node node = new Node(val);
            node.next = prev.next;
            prev.next.prev = node;
            prev.next = node;
            node.prev = prev;
            size++;
        }
    }
}

