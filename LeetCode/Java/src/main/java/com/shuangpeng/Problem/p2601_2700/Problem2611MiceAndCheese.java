package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2611MiceAndCheese（老鼠和奶酪）
 * @date 2023/6/7 10:20 AM
 */
public class Problem2611MiceAndCheese {

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        if (k == 0) {
            return Arrays.stream(reward2).sum();
        }
        int n = reward1.length, ans = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(k);
        for (int i = 0; i < n; i++) {
            ans += reward2[i];
            int d = reward1[i] - reward2[i];
            if (q.size() < k || q.peek() < d) {
                if (q.size() == k) {
                    ans -= q.poll();
                }
                ans += d;
                q.offer(d);
            }
        }
        return ans;
    }
}
