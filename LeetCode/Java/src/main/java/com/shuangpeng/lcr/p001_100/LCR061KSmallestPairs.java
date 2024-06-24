package com.shuangpeng.lcr.p001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR061KSmallestPairs（查找和最小的 K 对数字）
 * @date 2024/6/25 12:19 AM
 */
public class LCR061KSmallestPairs {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        for (int i = 0; i < n1; i++) {
            pq.offer(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            int[] p = pq.poll();
            ans.add(Arrays.asList(nums1[p[0]], nums2[p[1]]));
            if (++p[1] < n2) {
                p[2] = nums1[p[0]] + nums2[p[1]];
                pq.offer(p);
            }
        }
        return ans;
    }
}
