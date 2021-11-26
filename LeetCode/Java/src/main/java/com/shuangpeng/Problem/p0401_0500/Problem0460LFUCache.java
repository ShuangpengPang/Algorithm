package com.shuangpeng.Problem.p0401_0500;

import java.util.HashMap;
import java.util.Map;

public class Problem0460LFUCache {
}

class LFUCache {

    class FreqNode {
        int freq;
        FreqNode prev;
        FreqNode next;
        ValueNode head;
        ValueNode tail;
        FreqNode(int freq) {
            this.freq = freq;
            head = new ValueNode(-1, -1);
            tail = new ValueNode(-1, -1);
            head.next = tail;
            tail.prev = head;
        }
    }

    class ValueNode {
        int key;
        int value;
        int freq;
        ValueNode prev;
        ValueNode next;
        ValueNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    FreqNode freqHead;
    FreqNode freqTail;
    Map<Integer, ValueNode> valueMap;
    Map<Integer, FreqNode> freqNodeMap;
    int capacity;

    public LFUCache(int capacity) {
        freqHead = new FreqNode(0);
        freqTail = new FreqNode(-1);
        freqHead.next = freqTail;
        freqTail.prev = freqHead;
        valueMap = new HashMap<>(capacity);
        freqNodeMap = new HashMap<>();
        freqNodeMap.put(0, freqHead);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        ValueNode valueNode = valueMap.get(key);
        if (valueNode == null) {
            return -1;
        }
        int oldFreq = valueNode.freq;
        removeValueNode(valueNode, key);
        insertValueNode(valueNode, key, oldFreq + 1);
        checkFreqNode(oldFreq);
        return valueNode.value;
    }

    public void put(int key, int value) {
        ValueNode valueNode = valueMap.get(key);
        if (valueNode == null) {
            if (valueMap.size() >= capacity) {
                removeLFU();
            }
            valueNode = new ValueNode(key, value);
            insertValueNode(valueNode, key, 1);
        } else {
            int freq = valueNode.freq;
            removeValueNode(valueNode, key);
            valueNode.value = value;
            insertValueNode(valueNode, key, freq + 1);
            checkFreqNode(freq);
        }
    }

    private void insertValueNode(ValueNode valueNode, int key, int freq) {
        FreqNode freqNode = freqNodeMap.get(freq);
        if (freqNode == null) {
            insertNewFreqNode(freq);
            freqNode = freqNodeMap.get(freq);
        }
        insertValueNodeToFreqNode(freqNode, valueNode);
        valueNode.freq = freq;
        valueMap.put(key, valueNode);
    }

    private void insertNewFreqNode(int freq) {
        FreqNode preNode = freqNodeMap.get(freq - 1);
        FreqNode freqNode = new FreqNode(freq);
        freqNode.next = preNode.next;
        preNode.next.prev = freqNode;
        preNode.next = freqNode;
        freqNode.prev = preNode;
        freqNodeMap.put(freq, freqNode);
    }

    private void insertValueNodeToFreqNode(FreqNode freqNode, ValueNode valueNode) {
        ValueNode head = freqNode.head;
        valueNode.next = head.next;
        head.next.prev = valueNode;
        head.next = valueNode;
        valueNode.prev = head;
    }

    private void removeLFU() {
        if (freqHead.next == freqTail) {
            return;
        }
        ValueNode valueNode = freqHead.next.tail.prev;
        removeValueNode(valueNode, valueNode.key);
    }

    private void removeValueNode(ValueNode valueNode, int key) {
        valueNode.prev.next = valueNode.next;
        valueNode.next.prev = valueNode.prev;
        valueNode.prev = null;
        valueNode.next = null;
        valueMap.remove(key);
    }

    private void checkFreqNode(int freq) {
        FreqNode freqNode = freqNodeMap.get(freq);
        if (freqNode == null || freqNode == freqHead) {
            return;
        }
        if (freqNode.head.next == freqNode.tail) {
            freqNode.prev.next = freqNode.next;
            freqNode.next.prev = freqNode.prev;
            freqNode.prev = null;
            freqNode.next = null;
            freqNodeMap.remove(freqNode.freq);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
