package com.shuangpeng.Problem.p0201_0300;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0225ImplementStackUsingQueues（用队列实现栈）
 * @date 2024/1/24 12:10 AM
 */
public class Problem0225ImplementStackUsingQueues {

    class MyStack {

        private Queue<Integer> q;
        private int topNum;

        public MyStack() {
            q = new ArrayDeque<>();
        }

        public void push(int x) {
            topNum = x;
            q.offer(x);
        }

        public int pop() {
            int size = q.size();
            topNum = -1;
            for (int i = 1; i < size; i++) {
                topNum = q.poll();
                q.offer(topNum);
            }
            return q.poll();
        }

        public int top() {
            return topNum;
        }

        public boolean empty() {
            return q.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}

class Problem0225ImplementStackUsingQueues0 {

    class MyStack {

        private Queue<Integer> q;

        public MyStack() {
            q = new ArrayDeque<>();
        }

        public void push(int x) {
            q.offer(x);
            for (int i = q.size() - 2; i >= 0; i--) {
                q.offer(q.poll());
            }
        }

        public int pop() {
            return q.poll();
        }

        public int top() {
            return q.peek();
        }

        public boolean empty() {
            return q.isEmpty();
        }
    }
}
