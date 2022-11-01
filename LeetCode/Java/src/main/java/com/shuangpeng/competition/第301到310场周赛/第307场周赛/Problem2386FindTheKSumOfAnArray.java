package com.shuangpeng.competition.第301到310场周赛.第307场周赛;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Description: Problem2386FindTheKSumOfAnArray（找出数组的第K大和）
 * @Date 2022/10/29 6:03 PM
 * @Version 1.0
 */
public class Problem2386FindTheKSumOfAnArray {

    public long kSum(int[] nums, int k) {
        long sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
        }
        Arrays.sort(nums);
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>((a, b) -> Long.compare(b.getKey(), a.getKey()));
        pq.offer(new Pair<>(sum, 0));
        while (--k > 0) {
            Pair<Long, Integer> p = pq.poll();
            long s = p.getKey();
            int i = p.getValue();
            if (i < n) {
                pq.offer(new Pair<>(s - nums[i], i + 1));
                if (i > 0) {
                    pq.offer(new Pair<>(s - nums[i] + nums[i - 1], i + 1));
                }
            }
        }
        return pq.poll().getKey();
    }
}