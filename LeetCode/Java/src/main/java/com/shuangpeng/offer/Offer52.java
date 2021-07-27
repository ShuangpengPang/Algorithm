package com.shuangpeng.offer;

import com.shuangpeng.common.ListNode;

public class Offer52 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;
    }

//    public static void main(String[] args) {
//        ListNode c = new ListNode(8);
//        c.next = new ListNode(4);
//        c.next.next = new ListNode(5);
//
//        ListNode a = new ListNode(4);
//        a.next = new ListNode(1);
//        a.next.next = c;
//
//        ListNode b = new ListNode(5);
//        b.next = new ListNode(0);
//        b.next.next = new ListNode(1);
//        b.next.next.next = c;
//        Offer52 t = new Offer52();
//        t.getIntersectionNode0(a, b);
//    }
}
