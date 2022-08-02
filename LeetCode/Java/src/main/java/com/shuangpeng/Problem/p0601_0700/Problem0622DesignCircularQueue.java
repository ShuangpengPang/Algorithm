package com.shuangpeng.Problem.p0601_0700;

/**
 * @Description: Problem0622DesignCircularQueue（设计循环队列）
 * @Date 2022/8/2 9:58 AM
 * @Version 1.0
 */
public class Problem0622DesignCircularQueue {

    class MyCircularQueue {

        int capacity, cnt, head, tail;
        int[] nums;

        public MyCircularQueue(int k) {
            capacity = k;
            nums = new int[k];
            cnt = 0;
            head = tail = 0;
        }

        public boolean enQueue(int value) {
            if (cnt == capacity) {
                return false;
            }
            cnt++;
            nums[tail] = value;
            tail = (tail + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (cnt == 0) {
                return false;
            }
            cnt--;
            head = (head + 1) % capacity;
            return true;
        }

        public int Front() {
            if (cnt == 0) {
                return -1;
            }
            return nums[head];
        }

        public int Rear() {
            if (cnt == 0) {
                return -1;
            }
            return nums[(tail + capacity - 1) % capacity];
        }

        public boolean isEmpty() {
            return cnt == 0;
        }

        public boolean isFull() {
            return cnt == capacity;
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
}

