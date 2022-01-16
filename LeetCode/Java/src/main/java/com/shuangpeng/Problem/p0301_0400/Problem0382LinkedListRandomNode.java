package com.shuangpeng.Problem.p0301_0400;

import com.shuangpeng.common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0382LinkedListRandomNode {
}

class Solution {

    List<Integer> list;

    public Solution(ListNode head) {
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
