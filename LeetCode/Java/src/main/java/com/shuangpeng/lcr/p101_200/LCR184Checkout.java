package com.shuangpeng.lcr.p101_200;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR184Checkout（LCR 184. 设计自助结算系统）
 * @date 2024/8/10 6:57 PM
 */
public class LCR184Checkout {

    class Checkout {

        private Deque<int[]> stack;
        private Queue<Integer> q;
        private int firstIndex, index;

        public Checkout() {
            stack = new ArrayDeque<>();
            q = new ArrayDeque<>();
            firstIndex = index = 0;
        }

        public int get_max() {
            return stack.isEmpty() ? -1 : stack.peekFirst()[1];
        }

        public void add(int value) {
            while (!stack.isEmpty() && stack.peekLast()[1] <= value) {
                stack.pollLast();
            }
            q.offer(value);
            stack.offerLast(new int[]{index++, value});
        }

        public int remove() {
            if (q.isEmpty()) {
                return -1;
            }
            firstIndex++;
            while (!stack.isEmpty() && stack.peekFirst()[0] < firstIndex) {
                stack.pollFirst();
            }
            return q.poll();
        }
    }

/**
 * Your Checkout object will be instantiated and called as such:
 * Checkout obj = new Checkout();
 * int param_1 = obj.get_max();
 * obj.add(value);
 * int param_3 = obj.remove();
 */
}
