package com.shuangpeng.Problem.p0101_0200;

public class Problem0155MinStack {

    class MinStack {
        /** initialize your data structure here. */
//        ["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
//                [[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]

        long[] stack;
        long min = 0;
        int count = 0;
        int capacity = 0;

        public MinStack() {
            capacity = 500;
            stack = new long[capacity];
        }

        public void push(int x) {
            if (count == capacity) {
                capacity = capacity << 1;
                long[] newStack = new long[capacity];
                for (int i = 0; i < count; i++) {
                    newStack[i] = stack[i];
                }
                stack = newStack;
            }
            if (count == 0) {
                min = x;
                stack[count++] = 0;
            } else {
                long diff = x - min;
                stack[count++] = diff;
                if (diff < 0) {
                    min = x;
                }
            }
        }

        public void pop() {
            if (stack[count - 1] < 0) {
                min -= stack[count - 1];
            }
            count--;
        }

        public int top() {
            if (stack[count - 1] > 0) {
                return (int) (min + stack[count - 1]);
            } else {
                return (int) min;
            }
        }

        public int getMin() {
            return (int) min;
        }
    }
}
