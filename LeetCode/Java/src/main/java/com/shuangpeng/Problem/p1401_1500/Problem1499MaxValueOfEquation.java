package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem1499MaxValueOfEquation（满足不等式的最大值）
 * @Date 2022/8/23 10:57 PM
 * @Version 1.0
 */
public class Problem1499MaxValueOfEquation {

    public int findMaxValueOfEquation(int[][] points, int k) {
        int n = points.length;
        Deque<Integer> q = new ArrayDeque<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            while (!q.isEmpty() && x - points[q.peekFirst()][0] > k) {
                q.pollFirst();
            }
            if (!q.isEmpty()) {
                int idx = q.peekFirst();
                ans = Math.max(ans, x + y + points[idx][1] - points[idx][0]);
            }
            while (!q.isEmpty() && points[q.peekLast()][0] - points[q.peekLast()][1] >= x - y) {
                q.pollLast();
            }
            q.addLast(i);
        }
        return ans;
    }
}
