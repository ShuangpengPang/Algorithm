package com.shuangpeng.Problem.p1001_1100;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1090LargestValuesFromLabels（受标签影响的最大值）
 * @date 2023/5/21 11:56 PM
 */
public class Problem1090LargestValuesFromLabels {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int n = values.length;
        for (int i = 0; i < n; i++) {
            q.offer(new int[]{values[i], labels[i]});
        }
        int sum = 0, i = 0;
        while (i < numWanted && !q.isEmpty()) {
            int[] p = q.poll();
            int v = p[0], l = p[1];
            if (map.getOrDefault(l, 0) < useLimit) {
                sum += v;
                map.merge(l, 1, Integer::sum);
                i++;
            }
        }
        return sum;
    }
}
