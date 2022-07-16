package com.shuangpeng.offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: OfferII041MovingAverage（滑动窗口的平均值）
 * @Date 2022/7/16 11:43 AM
 * @Version 1.0
 */
public class OfferII041MovingAverage {

    class MovingAverage {

        Queue<Integer> queue;
        int size;
        int sum;

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            queue = new LinkedList<>();
            sum = 0;
            this.size = size;
        }

        public double next(int val) {
            if (queue.size() == size) {
                sum -= queue.poll();
            }
            queue.offer(val);
            sum += val;
            return (double) sum / queue.size();
        }
    }

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
}

class OfferII041MovingAverage0 {

    class MovingAverage {

        int[] array;
        int capacity, head = 0, tail = 0, count = 0;
        int sum = 0;

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            array = new int[size];
            capacity = size;
            head = 0;
            tail = 0;
            count = 0;
            sum = 0;
        }

        public double next(int val) {
            if (count == capacity) {
                sum -= array[head];
                head = (head + 1) % capacity;
            }
            sum += val;
            array[tail] = val;
            tail = (tail + 1) % capacity;
            if (count < capacity) {
                count++;
            }
            return (double) sum / count;
        }
    }
}

class MovingAverage {

    int[] array;
    int capacity, cursor, cnt, sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        array = new int[size];
        capacity = size;
        cursor = 0;
        cnt = 0;
        sum = 0;
    }

    public double next(int val) {
        sum += val - array[cursor];
        array[cursor] = val;
        cursor = (cursor + 1) % capacity;
        if (cnt < capacity) {
            cnt++;
        }
        return (double) sum / cnt;
    }
}
