package com.shuangpeng.lcr.p001_100;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR060TopKFrequent
 * @date 2024/6/25 11:38 AM
 */
public class LCR060TopKFrequent {

    public int[] topKFrequent0(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)  {
            freq.merge(num, 1, Integer::sum);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, Comparator.comparingInt(e -> e.getValue()));
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (pq.size() < k || entry.getValue() > pq.peek().getValue()) {
                if (pq.size() == k) {
                    pq.poll();
                }
                pq.offer(entry);
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll().getKey();
        }
        return ans;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.merge(num, 1, Integer::sum);
        }
        int n = freq.size();
        int[][] arr = new int[n][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            arr[index][0] = entry.getKey();
            arr[index][1] = entry.getValue();
            index++;
        }
        partition(arr, k);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i][0];
        }
        return ans;
    }

    private void partition(int[][] arr, int k) {
        int n = arr.length;
        if (n <= k) {
            return;
        }
        int s = 0, e = n - 1;
        while (s < k && e >= k) {
            int mid = s + e >> 1, pivot = arr[mid][1];
            swap(arr, mid, e);
            int p = s;
            for (int i = s; i < e; i++) {
                if (arr[i][1] > pivot) {
                    swap(arr, p, i);
                    p++;
                }
            }
            swap(arr, p, e);
            if (p >= k) {
                e = p - 1;
            } else {
                s = p + 1;
            }
        }
    }

    private void swap(int[][] arr, int i, int j) {
        if (i != j) {
            int[] tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
