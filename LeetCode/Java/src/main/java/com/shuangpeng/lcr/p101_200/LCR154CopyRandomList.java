package com.shuangpeng.lcr.p101_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR154CopyRandomList（LCR 154. 复杂链表的复制）
 * @date 2024/9/26 4:08 PM
 */
public class LCR154CopyRandomList {

    public Node copyRandomList0(Node head) {
        Node dummy = new Node(0), prev = dummy;
        Map<Node, Node> map = new HashMap<>();
        for (Node node = head; node != null; node = node.next) {
            Node copy = new Node(node.val);
            prev.next = copy;
            prev = copy;
            map.put(node, copy);
        }
        for (Node node = head, copy = dummy.next; node != null; node = node.next) {
            if (node.random != null) {
                copy.random = map.get(node.random);
            }
            copy = copy.next;
        }
        return dummy.next;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node node = head;
        while (node != null) {
            Node next = node.next;
            Node copy = new Node(node.val);
            copy.next = next;
            node.next = copy;
            node = next;
        }
        node = head;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }
        Node copyHead = head.next, cur = copyHead;
        node = head;
        while (cur.next != null)  {
            node.next = node.next.next;
            cur.next = cur.next.next;
            node = node.next;
            cur = cur.next;
        }
        node.next = null;
        return copyHead;
    }
}

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
