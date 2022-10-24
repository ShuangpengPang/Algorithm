package com.shuangpeng.Problem.p1801_1900;

/**
 * @Description: Problem1866NumberOfWaysToRearrangeSticksWithKSticksVisible（恰有K根木棍可以看到的排列数目）
 * @Date 2022/10/24 2:16 PM
 * @Version 1.0
 */
public class Problem1866NumberOfWaysToRearrangeSticksWithKSticksVisible {

    public int rearrangeSticks(int n, int k) {
        int M = (int) 1e9 + 7;
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = Math.min(i, k - 1); j >= 0; j--) {
                dp[j] = (int) (((long) dp[j] * i + (j > 0 ? dp[j - 1] : 0)) % M);
            }
        }
        return dp[k - 1];
    }
}
