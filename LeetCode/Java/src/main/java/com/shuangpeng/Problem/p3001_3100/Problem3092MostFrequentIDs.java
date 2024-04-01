package com.shuangpeng.Problem.p3001_3100;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3092MostFrequentIDs（最高频率的ID）
 * @date 2024/4/1 12:12 AM
 */
public class Problem3092MostFrequentIDs {

    public long[] mostFrequentIDs0(int[] nums, int[] freq) {
        Map<Integer, Long> map = new HashMap<>();
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[1], a[1]));
        int n = nums.length;
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            long f = map.merge(nums[i], (long) freq[i], Long::sum);
            pq.offer(new long[]{nums[i], f});
            while (pq.peek()[1] != map.get((int) pq.peek()[0])) {
                pq.poll();
            }
            ans[i] = pq.peek()[1];
        }
        return ans;
    }

    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Map<Integer, Long> map = new HashMap<>();
        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        int n = nums.length;
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i]) && treeMap.containsKey(map.get(nums[i]))) {
                long k = map.get(nums[i]);
                if (treeMap.get(k) == 1) {
                    treeMap.remove(k);
                } else {
                    treeMap.put(k, treeMap.get(k) - 1);
                }
            }
            treeMap.merge(map.merge(nums[i], (long) freq[i], Long::sum), 1, Integer::sum);
            ans[i] = treeMap.lastKey();
        }
        return ans;
    }
}
