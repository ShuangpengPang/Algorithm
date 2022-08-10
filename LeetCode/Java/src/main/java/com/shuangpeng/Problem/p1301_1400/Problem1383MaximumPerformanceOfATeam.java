package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Description: Problem1383MaximumPerformanceOfATeam（最大的团队表现值）
 * @Date 2022/8/10 11:09 AM
 * @Version 1.0
 */
public class Problem1383MaximumPerformanceOfATeam {

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = efficiency[i];
            pairs[i][1] = speed[i];
        }
        Arrays.sort(pairs, (a, b) -> b[0] - a[0]);
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a);
        long ans = 0L, sum = 0L;
        for (int i = 0; i < k; i++) {
            sum += pairs[i][1];
            minQ.offer(pairs[i][1]);
            ans = Math.max(ans, sum * pairs[i][0]);
        }
        for (int i = k; i < n; i++) {
            while (!maxQ.isEmpty() && maxQ.peek() > minQ.peek()) {
                sum += maxQ.peek() - minQ.peek();
                maxQ.offer(minQ.poll());
                minQ.offer(maxQ.poll());
            }
            sum -= minQ.peek();
            maxQ.offer(minQ.poll());
            minQ.offer(pairs[i][1]);
            sum += pairs[i][1];
            ans = Math.max(ans, sum * pairs[i][0]);
        }
        return(int) (ans % ((int) 1e9 + 7));
    }
}
