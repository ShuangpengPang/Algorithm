package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2585NumberOfWaysToEarnPoints（获得分数的方案数）
 * @date 2024/1/30 12:53 PM
 */
public class Problem2585NumberOfWaysToEarnPoints {

    public int waysToReachTarget(int target, int[][] types) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        int N = (int) 1e9 + 7;
        for (int[] t : types) {
            for (int i = target; i >= t[1]; i--) {
                for (int j = 0, s = 0; j < t[0] && i - s - t[1] >= 0; j++) {
                    s += t[1];
                    dp[i] = (dp[i] + dp[i - s]) % N;
                }
            }
        }
        return dp[target];
    }
}
