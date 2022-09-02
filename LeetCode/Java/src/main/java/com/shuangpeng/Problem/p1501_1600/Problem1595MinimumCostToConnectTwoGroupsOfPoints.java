package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1595MinimumCostToConnectTwoGroupsOfPoints（连通两组点的最小成本）
 * @Date 2022/9/2 11:46 AM
 * @Version 1.0
 */
public class Problem1595MinimumCostToConnectTwoGroupsOfPoints {

    // 复杂度较高
    public int connectTwoGroups0(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        int[][] costMatrix = new int[m][1 << n];
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < (1 << n); i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0)
                        sum += cost.get(k).get(j);
                }
                costMatrix[k][i] = sum;
            }
        }
        int[][] dp = new int[m][1 << n];
        for (int i = 1; i < m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0] = costMatrix[0];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < (1 << n); j++)
                for (int k = 1; k < (1 << n); k++)
                    dp[i][j | k] = Math.min(dp[i][j | k], dp[i - 1][k] + costMatrix[i][j]);
        return dp[m - 1][(1 << n) - 1];
    }

    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size(), N = 1 << n;
        int[][] dp = new int[m + 1][N];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE >> 1);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j < N; j++) {
                for (int k = 0; k < n; k++) {
                    if ((j & (1 << k)) != 0) {
                        dp[i][j] = Math.min(dp[i][j], Math.min(dp[i][j ^ (1 << k)], Math.min(dp[i - 1][j], dp[i - 1][j ^ (1 << k)])) + cost.get(i - 1).get(k));
                    }
                }
            }
        }
        return dp[m][N - 1];
    }
}
