package com.shuangpeng.Problem;

import com.shuangpeng.common.ListNode;

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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
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
}
