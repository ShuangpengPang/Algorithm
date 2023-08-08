package com.shuangpeng.Problem.p0301_0400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: 查找和最小的K对数字
 * @date 2023/5/30 7:44 PM
 **/
public class Problem0373FindKPairsWithSmallestSums {

    public List<List<Integer>> kSmallestPairs0(int[] nums1, int[] nums2, int k) {
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

    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length, s = getSum(nums1, nums2, k);
        List<List<Integer>> ans = new ArrayList<>(k);
        for (int i = 0; i < n1 && ans.size() < k; i++) {
            for (int j = 0; j < n2 && ans.size() < k && nums1[i] + nums2[j] < s; j++) {
                ans.add(Arrays.asList(nums1[i], nums2[j]));
            }
        }
        for (int i = 0, j = n2 - 1; i < n1 && ans.size() < k && j >= 0; i++) {
            while (j >= 0 && nums1[i] + nums2[j] > s) {
                j--;
            }
            for (int t = j; t >= 0 && nums1[i] + nums2[t] == s && ans.size() < k; t--) {
                ans.add(Arrays.asList(nums1[i], nums2[t]));
            }
        }
        return ans;
    }

    private int getSum(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int left = nums1[0] + nums2[0], right = nums1[n1 - 1] + nums2[n2 - 1];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (binarySearch(nums1, nums2, mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private long binarySearch(int[] nums1, int[] nums2, int sum) {
        int n1 = nums1.length, n2 = nums2.length;
        long cnt = 0;
        for (int i = 0, j = n2 - 1; i < n1 && j >= 0; i++) {
            while (j >= 0 && nums1[i] + nums2[j] > sum) {
                j--;
            }
            cnt += j + 1;
        }
        return cnt;
    }

//    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//        int n1 = nums1.length, n2 = nums2.length;
//        k = (int) Math.min(k, (long) n1 * n2);
//        int[] next = new int[n1];
//        List<List<Integer>> ans = new ArrayList<>(k);
//        for (int i = 0; i < k; i++) {
//            int sum = Integer.MAX_VALUE;
//            int t = 0;
//            for (int j = 0; j < n1; j++) {
//                if (next[j] < n2 && nums1[j] + nums2[next[j]] < sum) {
//                    t = j;
//                    sum = nums1[j] + nums2[next[j]];
//                }
//            }
//            ans.add(Arrays.asList(nums1[t], nums2[next[t]++]));
//        }
//        return ans;
//    }

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length, m = Math.min(n1, k);
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, Comparator.comparingInt(a -> nums1[a[0]] + nums2[a[1]]));
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i, 0});
        }
        List<List<Integer>> ans = new ArrayList<>(k);
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            int[] p = pq.poll();
            ans.add(Arrays.asList(nums1[p[0]], nums2[p[1]]));
            if (++p[1] < n2) {
                pq.offer(p);
            }
        }
        return ans;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        PriorityQueue<int[]> q = new PriorityQueue<>(k, Comparator.comparingInt(a -> nums1[a[0]] + nums2[a[1]]));
        q.offer(new int[]{0, 0});
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k && !q.isEmpty(); i++) {
            int[] p = q.poll();
            ans.add(Arrays.asList(nums1[p[0]], nums2[p[1]]));
            if (p[1] == 0 && p[0] < n1 - 1) {
                q.offer(new int[]{p[0] + 1, 0});
            }
            if (++p[1] < n2) {
                q.offer(p);
            }
        }
        return ans;
    }
}
