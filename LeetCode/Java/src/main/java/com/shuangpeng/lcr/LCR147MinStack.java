package com.shuangpeng.lcr;

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

        private Deque<Integer> stack;
        private Deque<int[]> indexDeque;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new ArrayDeque<>();
            indexDeque = new ArrayDeque<>();
        }

        public void push(int x) {
            if (indexDeque.isEmpty() || x < indexDeque.peekLast()[1]) {
                indexDeque.offerLast(new int[]{stack.size(), x});
            }
            stack.offerLast(x);
        }

        public void pop() {
            stack.pollLast();
            if (indexDeque.peekLast()[0] == stack.size()) {
                indexDeque.pollLast();
            }
        }

        public int top() {
            return stack.peekLast();
        }

        public int getMin() {
            return indexDeque.peekLast()[1];
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
        private static int[] stack = new int[N], indexStack = new int[N];
        private static int top = 0, indexTop = 0;

        /** initialize your data structure here. */
        public MinStack() {
            top = indexTop = 0;
        }

        public void push(int x) {
            if (indexTop == 0 || stack[indexStack[indexTop - 1]] > x) {
                indexStack[indexTop++] = top;
            }
            stack[top++] = x;
        }

        public void pop() {
            if (indexStack[indexTop - 1] == --top) {
                indexTop--;
            }
        }

        public int top() {
            return stack[top - 1];
        }

        public int getMin() {
            return stack[indexStack[indexTop - 1]];
        }
    }
}
