package com.shuangpeng.Problem.p0201_0300;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0232ImplementQueueUsingStacks {

    class MyQueue {

        Deque<Integer> pushStack;
        Deque<Integer> popStack;

        /** Initialize your data structure here. */
        public MyQueue() {
            pushStack = new LinkedList<>();
            popStack = new LinkedList<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            pushStack.offerLast(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.offerLast(pushStack.pollLast());
                }
            }
            return popStack.pollLast();
        }

        /** Get the front element. */
        public int peek() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.offerLast(pushStack.pollLast());
                }
            }
            return popStack.peekLast();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return pushStack.isEmpty() && popStack.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
