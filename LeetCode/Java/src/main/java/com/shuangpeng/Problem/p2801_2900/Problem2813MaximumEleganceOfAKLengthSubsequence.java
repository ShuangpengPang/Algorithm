package com.shuangpeng.Problem.p2801_2900;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2813MaximumEleganceOfAKLengthSubsequence（子序列最大优雅度）
 * @date 2023/8/19 3:35 PM
 */
public class Problem2813MaximumEleganceOfAKLengthSubsequence {

    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        long ans = 0, sum = 0;
        int cnt = 0, n = items.length;
        boolean[] category = new boolean[n];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            int p = items[i][0], c = items[i][1] - 1;
            sum += p;
            if (!category[c]) {
                cnt++;
                category[c] = true;
            } else {
                q.offer(p);
            }
        }
        ans = sum + (long) cnt * cnt;
        for (int i = k; i < n; i++) {
            int p = items[i][0], c = items[i][1] - 1;
            if (!category[c] && !q.isEmpty()) {
                category[c] = true;
                sum += p - q.poll();
                cnt++;
                ans = Math.max(ans, sum + (long) cnt * cnt);
            }
        }
        return ans;
    }
}
