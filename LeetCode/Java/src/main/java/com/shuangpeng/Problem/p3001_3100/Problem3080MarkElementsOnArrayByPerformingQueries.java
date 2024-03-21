package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3080MarkElementsOnArrayByPerformingQueries（执行操作标记数组中的元素）
 * @date 2024/3/22 12:17 AM
 */
public class Problem3080MarkElementsOnArrayByPerformingQueries {

    public long[] unmarkedSumArray0(int[] nums, int[][] queries) {
        long sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            pq.offer(i);
        }
        int m = queries.length;
        long[] ans = new long[m];
        boolean[] mark = new boolean[n];
        for (int i = 0; i < m && sum != 0; i++) {
            int index = queries[i][0], count = queries[i][1];
            if (!mark[index]) {
                sum -= nums[index];
                mark[index] = true;
            }
            for (int j = 0; j < count && !pq.isEmpty(); j++) {
                while (!pq.isEmpty() && mark[pq.peek()]) {
                    pq.poll();
                }
                if (!pq.isEmpty()) {
                    int idx = pq.poll();
                    sum -= nums[idx];
                    mark[idx] = true;
                }
            }
            ans[i] = sum;
        }
        return ans;
    }

    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        boolean[] mark = new boolean[n];
        int m = queries.length;
        long[] ans = new long[m];
        for (int i = 0, j = 0; i < m && j < n; i++) {
            int idx = queries[i][0], count = queries[i][1];
            if (!mark[idx]) {
                sum -= nums[idx];
                mark[idx] = true;
            }
            for (int k = 0; k < count && j < n; k++) {
                while (j < n && mark[ids[j]]) {
                    j++;
                }
                if (j < n) {
                    sum -= nums[ids[j]];
                    mark[ids[j]] = true;
                }
            }
            ans[i] = sum;
        }
        return ans;
    }
}
