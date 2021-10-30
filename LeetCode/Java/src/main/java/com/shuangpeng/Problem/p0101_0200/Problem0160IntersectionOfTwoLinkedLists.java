package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Problem0160IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lengthA = 0;
        int lengthB = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null) {
            lengthA++;
            curA = curA.next;
        }
        while (curB != null) {
            lengthB++;
            curB = curB.next;
        }
        int diff = Math.max(lengthA, lengthB) - Math.min(lengthA, lengthB);
        ListNode longList = headA;
        ListNode shortList = headB;
        if (lengthB > lengthA) {
            longList = headB;
            shortList = headA;
        }
        for (int i = 0; i < diff; i++) {
            longList = longList.next;
        }
        while (longList != null && longList != shortList) {
            longList = longList.next;
            shortList = shortList.next;
        }
        return longList;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB && (pA != null || pB != null)) {
            if (pA == null) {
                pA = headB;
            } else if (pB == null) {
                pB = headA;
            } else {
                pA = pA.next;
                pB = pB.next;
            }
        }
        return pA;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode node = headA;
        while (node != null) {
            set.add(node);
            node = node.next;
        }
        node = headB;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB || pA != null || pB != null) {
            if (pA == null) {
                pA = headB;
            }
            if (pB == null) {
                pB = headA;
            }
            if (pA == pB) {
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }
}
