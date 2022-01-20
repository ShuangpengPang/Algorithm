package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;

public class Problem0891SumOfSubsequenceWidths {

    public int sumSubseqWidths0(int[] nums) {
        final int M = (int) 1e9 + 7;
        int n = nums.length;
        int[] factor = new int[n];
        factor[0] = 1;
        for (int i = 1; i < n; ++i) {
            factor[i] = factor[i - 1] << 1;
            if (factor[i] >= M) {
                factor[i] -= M;
            }
        }
        Arrays.sort(nums);
        long[] dp = new long[n];
        for (int i = 1; i < n; ++i) {
            dp[i] = dp[i - 1] << 1;
            dp[i] += (long) (nums[i] - nums[i - 1]) * (factor[i] - 1);
            dp[i] %= M;
        }
        return (int) (Arrays.stream(dp).sum() % M);
    }

    public int sumSubseqWidths(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;
        Arrays.sort(A);

        long[] pow2 = new long[N];
        pow2[0] = 1;
        for (int i = 1; i < N; ++i)
            pow2[i] = pow2[i-1] * 2 % MOD;

        long ans = 0;
        for (int i = 0; i < N; ++i)
            ans = (ans + (pow2[i] - pow2[N-1-i]) * A[i]) % MOD;

        return (int) ans;
    }
}
