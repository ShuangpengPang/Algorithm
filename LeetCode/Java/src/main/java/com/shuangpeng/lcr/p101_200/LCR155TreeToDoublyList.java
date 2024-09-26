package com.shuangpeng.lcr.p101_200;

import com.shuangpeng.common.Node;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR155TreeToDoublyList（LCR 155. 将二叉搜索树转化为排序的双向链表）
 * @date 2024/8/3 10:32 AM
 */
public class LCR155TreeToDoublyList {

    public Node treeToDoublyList0(Node root) {
        if (root == null) {
            return null;
        }
        Node node = root, last = root;
        Node dummy = new Node(), prev = dummy;
        while (node != null) {
            last = node;
            if (node.left != null) {
                Node cur = node.left;
                while (cur.right != null && cur.right != node) {
                    cur = cur.right;
                }
                if (cur.right == null) {
                    cur.right = node;
                    node = node.left;
                } else {
                    prev = node;
                    node.left = cur;
                    node = node.right;
                }
            } else {
                prev.right = node;
                node.left = prev;
                prev = node;
                node = node.right;
            }
        }
        last.right = dummy.right;
        dummy.right.left = last;
        return dummy.right;
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node head = null, last = null, node = root;
        while (node != null) {
            if (node.left != null) {
                Node cur = node.left;
                while (cur.right != null && cur.right != node) {
                    cur = cur.right;
                }
                if (cur.right == null) {
                    cur.right = node;
                    node = node.left;
                    continue;
                }
            }
            if (head == null) {
                head = node;
            } else {
                last.right = node;
                node.left = last;
            }
            last = node;
            node = node.right;
        }
        head.left = last;
        last.right = head;
        return head;
    }
}
