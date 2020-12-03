package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.Map;

public class Problem0146LRUCache {

    class LRUCache {
        class Node {
            Node prev;
            Node next;
            int key;
            int value;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            Node() {
            }
        }

        int capacity;
        Map<Integer, Node> map;
        Node head;
        Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            head = new Node();
            tail = null;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                delete(node);
                insert(node.key, node.value);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                delete(map.get(key));
            } else if (map.size() == this.capacity) {
                delete(tail);
            }
            insert(key, value);
        }

        public void insert(int key, int value) {
            Node node = new Node(key, value);
            node.next = head.next;
            node.prev = head;
            if (head.next != null) {
                head.next.prev = node;
            } else {
                tail = node;
            }
            head.next = node;
            map.put(key, node);
        }

        public void delete(Node node) {
            Node prev = node.prev;
            prev.next = node.next;
            if (node.next != null) {
                node.next.prev = prev;
            } else {
                tail = prev;
            }
            node.prev = null;
            node.next = null;
            map.remove(node.key);
        }
    }
}
