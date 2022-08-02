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

class Problem0622DesignCircularQueue0 {

    class MyCircularQueue {

        private int capacity, front, rear;
        private int[] elements;

        public MyCircularQueue(int k) {
            capacity = k + 1;
            elements = new int[capacity];
            front = rear = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            elements[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + capacity + 1) % capacity;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return elements[front];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return elements[(rear + capacity - 1) % capacity];
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return (rear + 1) % capacity == front;
        }
    }
}

class MyCircularQueue {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head, tail;
    private int capacity, size;

    public MyCircularQueue(int k) {
        capacity = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (size == capacity) {
            return false;
        }
        size++;
        ListNode node = new ListNode(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        return true;
    }

    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        size--;
        head = head.next;
        return true;
    }

    public int Front() {
        if (size == 0) {
            return -1;
        }
        return head.val;
    }

    public int Rear() {
        if (size == 0) {
            return -1;
        }
        return tail.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}




