package com.shuangpeng.interview;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0305SortedStack（面试题 03.05. 栈排序）
 * @date 2024/8/26 12:06 PM
 */
public class Question0305SortedStack {

    class SortedStack {

        private Deque<Integer> stack, tmp;

        public SortedStack() {
            stack = new ArrayDeque<>();
            tmp = new ArrayDeque<>();
        }

        public void push(int val) {
            while (!stack.isEmpty() && stack.peekLast() < val) {
                tmp.offerLast(stack.pollLast());
            }
            while (!tmp.isEmpty() && tmp.peekLast() > val) {
                stack.offerLast(tmp.pollLast());
            }
            stack.offerLast(val);
        }

        public void pop() {
            if (!stack.isEmpty() && (tmp.isEmpty() || stack.peekLast() <= tmp.peekFirst())) {
                stack.pollLast();
            } else if (!tmp.isEmpty()) {
                tmp.pollFirst();
            }
        }

        public int peek() {
            if (!stack.isEmpty() && (tmp.isEmpty() || stack.peekLast() <= tmp.peekFirst())) {
                return stack.peekLast();
            } else if (!tmp.isEmpty()) {
                return tmp.peekFirst();
            }
            return -1;
        }

        public boolean isEmpty() {
            return stack.isEmpty() && tmp.isEmpty();
        }
    }

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */
}
