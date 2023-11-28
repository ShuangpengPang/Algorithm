package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2542MaximumSubsequenceScore（最大子序列的分数）
 * @date 2023/11/28 9:30 AM
 */
public class Problem2542MaximumSubsequenceScore {

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> {
            if (nums2[a] != nums2[b]) {
                return nums2[a] - nums2[b];
            }
            if (nums1[a] != nums1[b]) {
                return nums1[a] - nums1[b];
            }
            return a - b;
        });
        PriorityQueue<Integer> q1 = new PriorityQueue<>((a, b) -> {
            if (nums2[a] != nums2[b]) {
                return nums2[a] - nums2[b];
            }
            if (nums1[a] != nums1[b]) {
                return nums1[a] - nums1[b];
            }
            return a - b;
        });
        PriorityQueue<Integer> q2 = new PriorityQueue<>((a, b) -> {
            if (nums1[a] != nums1[b]) {
                return nums1[b] - nums1[a];
            }
            if (nums2[a] != nums2[b]) {
                return nums2[b] - nums2[a];
            }
            return b - a;
        });
        for (int i = 0; i < n; i++) {
            q2.offer(i);
        }
        long ans = 0, sum = 0;
        for (int id : ids) {
            while (!q1.isEmpty() && nums2[q1.peek()] < nums2[id]) {
                sum -= nums1[q1.poll()];
            }
            while (q1.size() < k && !q2.isEmpty()) {
                while (!q2.isEmpty() && nums2[q2.peek()] < nums2[id]) {
                    q2.poll();
                }
                if (!q2.isEmpty()) {
                    int idx = q2.poll();
                    sum += nums1[idx];
                    q1.offer(idx);
                }
            }
            while (q1.size() > k) {
                sum -= nums1[q1.poll()];
            }
            if (q1.size() == k && q1.peek() == id) {
                ans = Math.max(ans, sum * nums2[id]);
            }
        }
        return ans;
    }
}
