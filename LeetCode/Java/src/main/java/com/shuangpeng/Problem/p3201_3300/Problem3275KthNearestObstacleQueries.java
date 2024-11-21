package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3275KthNearestObstacleQueries（第K近障碍物查询）
 * @date 2024/11/21 12:10 PM
 */
public class Problem3275KthNearestObstacleQueries {

    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        int[] ans = new int[n];
        if (k > n) {
            Arrays.fill(ans, -1);
            return ans;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < k; i++) {
            pq.offer(Math.abs(queries[i][0]) + Math.abs(queries[i][1]));
            ans[i] = -1;
        }
        ans[k - 1] = pq.peek();
        for (int i = k; i < n; i++) {
            int d = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
            if (d <  pq.peek()) {
                pq.poll();
                pq.offer(d);
            }
            ans[i] = pq.peek();
        }
        return ans;
    }
}

class Problem3275KthNearestObstacleQueries0 {

    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        int[] ans = new int[n];
        if (k > n) {
            Arrays.fill(ans, -1);
            return ans;
        }
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = -1;
            heap[i] = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
        }
        initialHeap(heap);
        ans[k - 1] = heap[0];
        for (int i = k; i < n; i++) {
            int d = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
            if (d < heap[0]) {
                heap[0] = d;
                sink(heap, 0);
            }
            ans[i] = heap[0];
        }
        return ans;
    }

    private void initialHeap(int[] heap) {
        int n = heap.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            sink(heap, i);
        }
    }

    private void sink(int[] heap, int i) {
        int n = heap.length, num = heap[i];
        int left = i * 2 + 1, right = left + 1;
        while (left < n) {
            int j = right == n || heap[left] >= heap[right] ? left : right;
            if (num >= heap[j]) {
                break;
            }
            heap[i] = heap[j];
            i = j;
            left = i * 2 + 1;
            right = left + 1;
        }
        heap[i] = num;
    }
}
