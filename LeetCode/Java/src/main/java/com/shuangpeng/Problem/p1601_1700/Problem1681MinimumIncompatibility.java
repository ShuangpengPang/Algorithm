package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;

/**
 * @Description: Problem1681MinimumIncompatibility（最小不兼容性）
 * @Date 2022/9/21 4:05 PM
 * @Version 1.0
 */
public class Problem1681MinimumIncompatibility {

    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length, N = 1 << n, avg = n / k, INF = Integer.MAX_VALUE >> 1;
        Arrays.sort(nums);
        int[] diff = new int[N];
        boolean[] valid = new boolean[N];
        for (int i = 0; i < N; i++) {
            int cnt = Integer.bitCount(i);
            if (cnt == avg) {
                int prev = -1;
                valid[i] = true;
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                for (int j = 0; j < n; j++) {
                    if (((i >> j) & 1) == 1) {
                        if (nums[j] == prev) {
                            valid[i] = false;
                            break;
                        }
                        prev = nums[j];
                        min = Math.min(min, nums[j]);
                        max = Math.max(max, nums[j]);
                    }
                }
                if (valid[i]) {
                    diff[i] = max - min;
                }
            }
        }
        int[] dp = new int[N];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i < N; i++) {
            int cnt = Integer.bitCount(i);
            if (cnt % avg == 0) {
                for (int j = i; j > 0; j = i & (j - 1)) {
                    if (valid[j]) {
                        dp[i] = Math.min(dp[i], dp[i ^ j] + diff[j]);
                    }
                }
            }
        }
        return dp[N - 1] == INF ? -1 : dp[N - 1];
    }
}
