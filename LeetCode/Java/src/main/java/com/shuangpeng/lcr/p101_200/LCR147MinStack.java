package com.shuangpeng.lcr.p101_200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR147MinStack（最小栈）
 * @date 2024/5/16 3:45 PM
 */
public class LCR147MinStack {

    class MinStack {

        private Deque<Integer> stack, minStack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int x) {
            if (minStack.isEmpty() || x <= minStack.peekLast()) {
                minStack.offerLast(x);
            }
            stack.offerLast(x);
        }

        public void pop() {
            int num = stack.pollLast();
            if (num == minStack.peekLast()) {
                minStack.pollLast();
            }
        }

        public int top() {
            return stack.peekLast();
        }

        public int getMin() {
            return minStack.peekLast();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}

class LCR147MinStack0 {

    static class MinStack {

        private static int N = (int) (3 * 1e4);
        private static int[] stack = new int[N], minStack = new int[N];
        private static int top = 0, minTop = 0;

        /** initialize your data structure here. */
        public MinStack() {
            top = minTop = 0;
        }

        public void push(int x) {
            if (minTop == 0 || minStack[minTop - 1] >= x) {
                minStack[minTop++] = x;
            }
            stack[top++] = x;
        }

        public void pop() {
            if (minStack[minTop - 1] == stack[--top]) {
                minTop--;
            }
        }

        public int top() {
            return stack[top - 1];
        }

        public int getMin() {
            return minStack[minTop - 1];
        }
    }
}

class MinStack {

    private static int N = (int) (3 * 1e4);
    private static int[] stack = new int[N], minStack = new int[N];
    private static int top = 0, minTop = 0;

    /** initialize your data structure here. */
    public MinStack() {
        top = minTop = 0;
    }

    public void push(int x) {
        if (minTop == 0 || minStack[minTop - 1] >= x) {
            minStack[minTop++] = x;
        }
        stack[top++] = x;
    }

    public void pop() {
        if (minStack[minTop - 1] == stack[--top]) {
            minTop--;
        }
    }

    public int top() {
        return stack[top - 1];
    }

    public int getMin() {
        return minStack[minTop - 1];
    }
}
