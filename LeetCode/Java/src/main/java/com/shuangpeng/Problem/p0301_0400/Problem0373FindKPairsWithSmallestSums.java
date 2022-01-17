package com.shuangpeng.Problem.p0301_0400;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Problem0373FindKPairsWithSmallestSums {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return recurse(nums1, nums2, k, false);
    }

    private List<List<Integer>> recurse(int[] nums1, int[] nums2, int k, boolean reverse) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) {
            return recurse(nums2, nums1, k, true);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> nums1[a[0]] + nums2[a[1]]));
        for (int i = 0; i < n1; ++i) {
            pq.offer(new int[]{i, 0});
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (!pq.isEmpty() && k > 0) {
            List<Integer> pair = new ArrayList<>();
            int[] indices = pq.poll();
            if (reverse) {
                pair.add(nums2[indices[1]]);
                pair.add(nums1[indices[0]]);
            } else {
                pair.add(nums1[indices[0]]);
                pair.add(nums2[indices[1]]);
            }
            ans.add(pair);
            ++indices[1];
            if (indices[1] < n2) {
                pq.offer(indices);
            }
            --k;
        }
        return ans;
    }
}
