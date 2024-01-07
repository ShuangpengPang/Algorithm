package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2919MinimumIncrementOperationsToMakeArrayBeautiful（使数组变美的最小增量运算数）
 * @date 2024/1/7 8:49 PM
 */
public class Problem2919MinimumIncrementOperationsToMakeArrayBeautiful {

    public long minIncrementOperations0(int[] nums, int k) {
        return dfs(nums, nums.length - 1, 0, k, new HashMap<>());
    }

    private long dfs(int[] nums, int i, int cnt, int k, Map<Integer, Long> memo) {
        if (i < 0) {
            return 0;
        }
        int key = (i + 1) * 10 + cnt;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long ans = dfs(nums, i - 1, 0, k, memo) + Math.max(0, k - nums[i]);
        if (cnt < 2) {
            ans = Math.min(ans, dfs(nums, i - 1, cnt + 1, k, memo));
        }
        memo.put(key, ans);
        return ans;
    }

    public long minIncrementOperations1(int[] nums, int k) {
        int n = nums.length;
        long[][] dp = new long[2][3];
        for (int i = 1; i <= n; i++) {
            int idx = i & 1, p = idx ^ 1;
            dp[idx][2] = dp[p][0] + Math.max(0, k - nums[i - 1]);
            for (int j = 0; j < 2; j++) {
                dp[idx][j] = Math.min(dp[p][j + 1], dp[idx][2]);
            }
        }
        return Arrays.stream(dp[n & 1]).min().getAsLong();
    }

    public long minIncrementOperations2(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[n + 1];
        for (int i = 2; i < n; i++) {
            long cnt = Long.MAX_VALUE;
            for (int j = i; j > i - 3; j--) {
                cnt = Math.min(cnt, dp[j] + Math.max(0, k - nums[j]));
            }
            dp[i + 1] = cnt;
        }
        return dp[n];
    }

    public long minIncrementOperations(int[] nums, int k) {
        long[] dp = new long[4];
        for (int i = 2; i < nums.length; i++) {
            long cnt = Long.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                cnt = Math.min(cnt, dp[3 - j] + Math.max(0, k - nums[i - j]));
            }
            for (int j = 1; j <= 3; j++) {
                dp[j - 1] = dp[j];
            }
            dp[3] = cnt;
        }
        return dp[3];
    }
}
