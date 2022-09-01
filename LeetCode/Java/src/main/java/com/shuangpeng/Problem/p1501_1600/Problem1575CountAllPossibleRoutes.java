package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;

/**
 * @Description: Problem1575CountAllPossibleRoutes（统计所有可行路径）
 * @Date 2022/9/1 3:11 PM
 * @Version 1.0
 */
public class Problem1575CountAllPossibleRoutes {

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int startLocation = locations[start], finishLocation = locations[finish];
        int startIdx = 0, finishIdx = 0, n = locations.length;
        Arrays.sort(locations);
        for (int i = 0; i < n; i++) {
            if (locations[i] == startLocation) {
                startIdx = i;
            }
            if (locations[i] == finishLocation) {
                finishIdx = i;
            }
        }
        int[][] dp = new int[n][fuel + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(locations, dp, startIdx, finishIdx, fuel);
    }

    private int dfs(int[] locations, int[][] dp, int start, int end, int fuel) {
        if (fuel < Math.abs(locations[start] - locations[end])) {
            return 0;
        }
        if (dp[start][fuel] != -1) {
            return dp[start][fuel];
        }
        int M = (int) 1e9 + 7;
        int ans = start == end ? 1 : 0, n = locations.length;
        for (int i = start - 1; i >= 0 && fuel - (locations[start] - locations[i]) >= 0; i--) {
            ans = (ans + dfs(locations, dp, i, end, fuel - (locations[start] - locations[i]))) % M;
        }
        for (int i = start + 1; i < n && fuel - (locations[i] - locations[start]) >= 0; i++) {
            ans = (ans + dfs(locations, dp, i, end, fuel - (locations[i] - locations[start]))) % M;
        }
        dp[start][fuel] = ans;
        return ans;
    }
}
