package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1595MinimumCostToConnectTwoGroupsOfPoints（连通两组点的最小成本）
 * @Date 2022/9/2 11:46 AM
 * @Version 1.0
 */
public class Problem1595MinimumCostToConnectTwoGroupsOfPoints {

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
