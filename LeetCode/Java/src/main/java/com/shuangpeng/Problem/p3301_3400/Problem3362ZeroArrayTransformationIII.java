package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3362ZeroArrayTransformationIII（零数组变换III）
 * @date 2025/4/2 10:59
 */
public class Problem3362ZeroArrayTransformationIII {

    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));
        int n = nums.length, m = queries.length;
        int[] diff = new int[n + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, j = 0, s = 0; i < n; i++) {
            s += diff[i];
            while (j < m && queries[j][0] == i) {
                pq.offer(queries[j++][1]);
            }
            while (s < nums[i]) {
                if (pq.isEmpty() || pq.peek() < i) {
                    return -1;
                }
                diff[pq.poll() + 1]--;
                s++;
            }
        }
        return pq.size();
    }
}
