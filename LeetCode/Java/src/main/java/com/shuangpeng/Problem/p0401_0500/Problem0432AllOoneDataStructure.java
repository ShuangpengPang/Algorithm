package com.shuangpeng.Problem.p0401_0500;

import java.util.*;

public class Problem0432AllOoneDataStructure {
}

class AllOne {

    class Node {
        Node prev;
        Node next;
        Set<String> values;
        int freq;

        Node(int freq) {
            values = new HashSet<>();
            this.freq = freq;
        }
    }

    Node head;
    Node tail;
    Map<String, Node> nodeMap;

    public AllOne() {
        head = new Node(0);
        tail = new Node(Integer.MIN_VALUE);
        head.values.add("");
        tail.values.add("");
        head.next = tail;
        tail.prev = head;
        nodeMap = new HashMap<>();
    }

    public void inc(String key) {
        Node node = nodeMap.getOrDefault(key, head);
        int freq = node.freq + 1;
        if (node.next.freq != freq) {
            Node curr = new Node(freq);
            curr.next = node.next;
            node.next.prev = curr;
            node.next = curr;
            curr.prev = node;
        }
        node.next.values.add(key);
        nodeMap.put(key, node.next);
        node.values.remove(key);
        if (node.values.isEmpty()) {
            removeNode(node);
        }
    }

    public void dec(String key) {
        Node node = nodeMap.get(key);
        int freq = node.freq - 1;
        if (freq > 0 && node.prev.freq != freq) {
            Node curr = new Node(freq);
            curr.prev = node.prev;
            node.prev.next = curr;
            curr.next = node;
            node.prev = curr;
        }
        if (freq > 0) {
            node.prev.values.add(key);
            nodeMap.put(key, node.prev);
        } else {
            nodeMap.remove(key);
        }
        node.values.remove(key);
        if (node.values.isEmpty()) {
            removeNode(node);
        }
    }

    public String getMaxKey() {
        return tail.prev.values.iterator().next();
    }

    public String getMinKey() {
        return head.next.values.iterator().next();
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
