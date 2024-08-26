package com.shuangpeng.interview;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0303StackOfPlates（面试题 03.03. 堆盘子）
 * @date 2024/8/26 4:03 PM
 */
public class Question0303StackOfPlates {
}

class StackOfPlates {

    private int cap;

    private List<Deque<Integer>> stacks;

    public StackOfPlates(int cap) {
        this.cap = cap;
        stacks = new LinkedList<>();
    }

    public void push(int val) {
        if (cap == 0) {
            return;
        }
        if (stacks.isEmpty() || stacks.get(stacks.size() - 1).size() == cap) {
            stacks.add(new ArrayDeque<>(cap));
        }
        stacks.get(stacks.size() - 1).offerLast(val);
    }

    public int pop() {
        if (cap == 0) {
            return -1;
        }
        if (stacks.isEmpty()) {
            return -1;
        }
        int index = stacks.size() - 1;
        int ans = stacks.get(index).pollLast();
        if (stacks.get(index).isEmpty()) {
            stacks.remove(index);
        }
        return ans;
    }

    public int popAt(int index) {
        if (cap == 0) {
            return -1;
        }
        if (index >= stacks.size()) {
            return -1;
        }
        int ans = stacks.get(index).pollLast();
        if (stacks.get(index).isEmpty()) {
            stacks.remove(index);
        }
        return ans;
    }
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates obj = new StackOfPlates(cap);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAt(index);
 */
