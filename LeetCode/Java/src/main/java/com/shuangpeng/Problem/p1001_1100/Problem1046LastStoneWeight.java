package com.shuangpeng.Problem.p1001_1100;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1046LastStoneWeight（最后一块石头的重量）
 * @date 2024/1/7 11:05 AM
 */
public class Problem1046LastStoneWeight {

    public int lastStoneWeight0(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int s : stones) {
            pq.offer(s);
        }
        while (pq.size() > 1) {
            int diff = pq.poll() - pq.poll();
            if (diff != 0) {
                pq.offer(diff);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        for (int i = n - 2 >> 1; i >= 0; i--) {
            int index = i, left = (index << 1) + 1, right = left + 1;
            int num = stones[i];
            while (left < n) {
                int child = right >= n || stones[left] >= stones[right] ? left : right;
                if (num >= stones[child]) {
                    break;
                }
                stones[index] = stones[child];
                index = child;
                left = (index << 1) + 1;
                right = left + 1;
            }
            stones[index] = num;
        }
        while (n > 1) {
            int diff = pop(stones, n--) - pop(stones, n--);
            if (diff != 0) {
                offer(stones, diff, n++);
            }
        }
        return n == 0 ? 0 : pop(stones, n);
    }

    private int pop(int[] heap, int n) {
        if (n == 0) {
            return -1;
        }
        n--;
        int ans = heap[0];
        int num = heap[n];
        heap[n] = 0;
        int index = 0, left = 1, right = 2;
        while (left < n) {
            int child = right >= n || heap[left] >= heap[right] ? left : right;
            if (num >= heap[child]) {
                break;
            }
            heap[index] = heap[child];
            index = child;
            left = (child << 1) + 1;
            right = left + 1;
        }
        heap[index] = num;
        return ans;
    }

    private void offer(int[] heap, int num, int n) {
        int index = n;
        while (index != 0) {
            int p = (index - 1) >> 1;
            if (heap[p] >= num) {
                break;
            }
            heap[index] = heap[p];
            index = p;
        }
        heap[index] = num;
    }
}
