package com.shuangpeng.Problem.p1801_1900;

/**
 * @Description: Problem1872StoneGameVIII（石头游戏VIII）
 * @Date 2022/10/24 6:22 PM
 * @Version 1.0
 */
public class Problem1872StoneGameVIII {

    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        int[] dp = new int[n];
        dp[n - 1] = preSum[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            dp[i] = Math.max(dp[i + 1], preSum[i] - dp[i + 1]);
        }
        return dp[1];
    }
}
