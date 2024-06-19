package com.shuangpeng.lcr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR031LRUCache（LRU缓存）
 * @date 2024/6/19 11:42 AM
 */
public class LCR031LRUCache {

    class LRUCache {

        class Node {
            int key, val;
            Node prev, next;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private int capacity;
        private Node head;
        private Map<Integer, Node> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new Node(-1, -1);
            head.prev = head.next = head;
            map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            remove(node);
            insertToHead(node);
            return map.get(key).val;
        }

        public void put(int key, int value) {
            Node node = map.computeIfAbsent(key, k -> new Node(key, value));
            if (node.prev != null) {
                remove(node);
            }
            node.val = value;
            insertToHead(node);
            if (map.size() > capacity) {
                remove(head.prev);
            }
        }

        private void insertToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            map.put(node.key, node);
        }

        private void remove(Node node) {
            map.remove(node.key);
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = node.next = null;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
