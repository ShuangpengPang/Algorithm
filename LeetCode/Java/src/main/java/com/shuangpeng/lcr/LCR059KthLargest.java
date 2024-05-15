package com.shuangpeng.lcr;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR059KthLargest（数据流中的第K大元素）
 * @date 2024/5/14 7:00 PM
 */
public class LCR059KthLargest {

    class KthLargest {

        int[] heap;
        int k, n;


        public KthLargest(int k, int[] nums) {
            this.k = k;
            heap = new int[k];
            int m = nums.length;
            n = Math.min(k, m);
            for (int i = 0; i < n; i++) {
                heap[i] = nums[i];
            }
            buildHeap();
            for (int i = n; i < m; i++) {
                if (nums[i] > heap[0]) {
                    heap[0] = nums[i];
                    sink(0);
                }
            }
        }

        public int add(int val) {
            if (n < k) {
                heap[n++] = val;
                rise(n - 1);
            } else if (val > heap[0]) {
                heap[0] = val;
                sink(0);
            }
            return heap[0];
        }

        private void buildHeap() {
            for (int i = (n >> 1) - 1; i >= 0; i--) {
                sink(i);
            }
        }

        private void sink(int i) {
            int num = heap[i];
            while ((i << 1) + 1 < n) {
                int left = (i << 1) + 1, right = left + 1;
                int j = right >= n || heap[left] <= heap[right] ? left : right;
                if (num <= heap[j]) {
                    break;
                }
                heap[i] = heap[j];
                i = j;
            }
            heap[i] = num;
        }

        private void rise(int i) {
            int num = heap[i], p = i - 1 >> 1;
            while (i > 0 && num < heap[p]) {
                heap[i] = heap[p];
                i = p;
                p = i - 1 >> 1;
            }
            heap[i] = num;
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

}

class KthLargest {

    private PriorityQueue<Integer> pq;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        int n = nums.length, m = Math.min(k, n);
        for (int i = 0; i < m; i++) {
            pq.offer(nums[i]);
        }
        for (int i = m; i < n; i++) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
    }

    public int add(int val) {
        if (pq.size() < k) {
            pq.offer(val);
        } else if (val > pq.peek()) {
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }
}
