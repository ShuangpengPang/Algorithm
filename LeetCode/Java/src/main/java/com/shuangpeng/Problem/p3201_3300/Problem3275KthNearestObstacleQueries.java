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
