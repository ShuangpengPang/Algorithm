package com.shuangpeng.lcr;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
            node.prev.next = node;
            node.next.prev = node;
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

class LCR031LRUCache0 {

    class LRUCache extends LinkedHashMap<Integer, Integer> {

        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }
}

class LCR031LRUCache1 {

    class LRUCache {
        private class Node {
            int key, value;
            Node prev, next;

            Node(int k, int v) {
                key = k;
                value = v;
            }
        }

        private final int capacity;
        private final Node dummy = new Node(0, 0); // 哨兵节点
        private final Map<Integer, Node> keyToNode = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            dummy.prev = dummy;
            dummy.next = dummy;
        }

        public int get(int key) {
            Node node = getNode(key);
            return node != null ? node.value : -1;
        }

        public void put(int key, int value) {
            Node node = getNode(key);
            if (node != null) { // 有这本书
                node.value = value; // 更新 value
                return;
            }
            node = new Node(key, value); // 新书
            keyToNode.put(key, node);
            pushFront(node); // 放在最上面
            if (keyToNode.size() > capacity) { // 书太多了
                Node backNode = dummy.prev;
                keyToNode.remove(backNode.key);
                remove(backNode); // 去掉最后一本书
            }
        }

        private Node getNode(int key) {
            if (!keyToNode.containsKey(key)) { // 没有这本书
                return null;
            }
            Node node = keyToNode.get(key); // 有这本书
            remove(node); // 把这本书抽出来
            pushFront(node); // 放在最上面
            return node;
        }

        // 删除一个节点（抽出一本书）
        private void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
        }

        // 在链表头添加一个节点（把一本书放在最上面）
        private void pushFront(Node x) {
            x.prev = dummy;
            x.next = dummy.next;
            x.prev.next = x;
            x.next.prev = x;
        }
    }
}
