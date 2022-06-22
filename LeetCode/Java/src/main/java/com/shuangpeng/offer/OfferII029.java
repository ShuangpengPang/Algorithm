package com.shuangpeng.offer;

import com.shuangpeng.common.Node;

/**
 * @Description: OfferII029（排序的循环链表）
 * @Date 2022/6/18 11:00 AM
 * @Version 1.0
 */
public class OfferII029 {

    public Node insert0(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node curr = head;
        while (curr.next != head && ((curr.val <= curr.next.val && (insertVal > curr.next.val || insertVal < curr.val)) || (insertVal < curr.val && insertVal > curr.next.val))) {
            curr = curr.next;
        }
        Node node = new Node(insertVal);
        node.next = curr.next;
        curr.next = node;
        return head;
    }

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node curr = head, next = head.next;
        while (next != head) {
            if (curr.val <= insertVal && insertVal <= next.val) {
                break;
            }
            if (curr.val > next.val && (insertVal >= curr.val || insertVal <= next.val)) {
                break;
            }
            curr = curr.next;
            next = next.next;
        }
        Node node = new Node(insertVal);
        curr.next = node;
        node.next = next;
        return head;
    }
}
