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

    public int[] topKFrequent(int[] nums, int k) {
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
}
