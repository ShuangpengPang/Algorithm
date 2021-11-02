package com.shuangpeng.Problem.p0501_0600;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem0502IPO {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] index = new int[n][2];
        for (int i = 0; i < n; ++i) {
            index[i][0] = capital[i];
            index[i][1] = i;
        }
        Arrays.sort(index, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int i = 0;
        while (k > 0) {
            while (i < n && w >= index[i][0]) {
                queue.offer(profits[index[i][1]]);
                i++;
            }
            if (queue.isEmpty()) {
                break;
            }
            w += queue.poll();
            k--;
        }
        return w;
    }
}
