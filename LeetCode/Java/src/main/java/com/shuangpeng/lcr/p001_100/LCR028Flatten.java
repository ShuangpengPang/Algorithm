package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR028Flatten（扁平化多级双向链表）
 * @date 2024/6/17 2:18 PM
 */
public class LCR028Flatten {

    private Node current;

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        current = new Node();
        dfs(head);
        head.prev = null;
        return head;
    }

    private void dfs(Node head) {
        if (head == null) {
            return;
        }
        current.next = head;
        current.child = null;
        head.prev = current;
        current = head;
        Node child = head.child, next = head.next;
        dfs(child);
        dfs(next);
    }
}

class LCR028Flatten0 {

    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    private Node dfs(Node head) {
        Node cur = head, last = null;
        while (cur != null) {
            if (cur.child != null) {
                Node childLast = dfs(cur.child), next = cur.next;;
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
                childLast.next = next;
                if (next != null) {
                    next.prev = childLast;
                }
                last = childLast;
                cur = next;
            } else {
                last = cur;
                cur = cur.next;
            }
        }
        return last;
    }
}

// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}
