package com.shuangpeng.Problem.p0901_1000;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem0933NumberOfRecentCalls
 * @Date 2022/5/6 11:30 AM
 * @Version 1.0
 */
public class Problem0933NumberOfRecentCalls {
}

class Problem0933NumberOfRecentCalls0 {
    class RecentCounter {

        Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.add(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }
}

class Problem0933NumberOfRecentCalls1 {
    class RecentCounter {

        int maxSize = 3005;
        int[] q;
        int start = 0, end = 0;

        public RecentCounter() {
            q = new int[maxSize];
            start = 0;
            end = 0;
        }

        public int ping(int t) {
            q[end] = t;
            ++end;
            if (end >= maxSize) {
                end -= maxSize;
            }
            while (q[start] < t - 3000) {
                ++start;
                if (start >= maxSize) {
                    start -= maxSize;
                }
            }
            return end >= start ? end - start : end + maxSize - start;
        }
    }
}


/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
