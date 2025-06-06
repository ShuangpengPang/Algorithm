package com.shuangpeng.Problem.p3201_3300;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3282ReachEndOfArrayWithMaxScore（到达数组末尾的最大得分）
 * @date 2024/11/27 4:22 PM
 */
public class Problem3282ReachEndOfArrayWithMaxScore {

    public long findMaximumScore0(List<Integer> nums) {
        int n = nums.size();
        long[] dp = new long[n];
        Deque<Integer> q = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            while (!q.isEmpty() && nums.get(q.peek()) < num) {
                int x = q.poll();
                dp[i] = Math.max(dp[i], dp[x] + (long) (i - x) * nums.get(x));
            }
            if (!q.isEmpty()) {
                int x = q.peek();
                dp[i] = Math.max(dp[i], dp[x] + (long) (i - x) * nums.get(x));
            }
            q.offer(i);
        }
        return dp[n - 1];
    }

    public long findMaximumScore1(List<Integer> nums) {
        int n = nums.size(), index = 0;
        long ans = 0;
        for (int i = 1; i < n - 1; i++) {
            int num = nums.get(i);
            if (num > nums.get(index)) {
                ans += (long) (i - index) * nums.get(index);
                index = i;
            }
        }
        return ans + (long) (n - 1 - index) * nums.get(index);
    }

    public long findMaximumScore(List<Integer> nums) {
        int n = nums.size(), mx = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += mx;
            mx = Math.max(mx, nums.get(i));
        }
        return ans;
    }
}
