package com.shuangpeng.Problem.p0301_0400;

import com.shuangpeng.common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem0382LinkedListRandomNode {
}

class Solution0 {

    List<Integer> list;

    public Solution0(ListNode head) {
        list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        int i = (int) (list.size() * Math.random());
        return list.get(i);
    }
}

class Solution {

    ListNode head;
    Random random;

    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        int ans = 0;
        ListNode node = head;
        int i = 0;
        while (node != null) {
            if (random.nextInt(i + 1) == 0) {
                ans = node.val;
            }
            node = node.next;
            ++i;
        }
        return ans;
    }
}
