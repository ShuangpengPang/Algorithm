package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1444NumberOfWaysOfCuttingAPizza（切披萨的方案数）
 * @Date 2022/8/17 5:32 PM
 * @Version 1.0
 */
public class Problem1444NumberOfWaysOfCuttingAPizza {

    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        int[][][] dp = new int[m][n][k];
        int appleCount = 0;
        for (int i = 0; i < m; i++) {
            String s = pizza[i];
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
                if (s.charAt(j) == 'A') {
                    appleCount++;
                }
            }
        }
        return dfs(pizza, dp, 0, 0, appleCount, k - 1);
    }

    private int dfs(String[] pizza, int[][][] dp, int x, int y, int appleCount, int k) {
        if (appleCount <= 0) {
            dp[x][y][k] = 0;
            return 0;
        }
        if (k == 0) {
            dp[x][y][k] = 1;
            return 1;
        }
        if (dp[x][y][k] != -1) {
            return dp[x][y][k];
        }
        int cnt = 0, m = pizza.length, n = pizza[0].length();
        long ans = 0L;
        for (int i = x + 1; i < m && cnt + k <= appleCount; i++) {
            String s = pizza[i - 1];
            for (int j = y; j < n; j++) {
                if (s.charAt(j) == 'A') {
                    cnt++;
                }
            }
            if (cnt > 0 && cnt + k <= appleCount) {
                ans += dfs(pizza, dp, i, y, appleCount - cnt, k - 1);
            }
        }
        cnt = 0;
        for (int j = y + 1; j < n && cnt + k <= appleCount; j++) {
            for (int i = x; i < m; i++) {
                if (pizza[i].charAt(j - 1) == 'A') {
                    cnt++;
                }
            }
            if (cnt > 0 && cnt + k <= appleCount) {
                ans += dfs(pizza, dp, x, j, appleCount - cnt, k - 1);
            }
        }
        ans = ans % ((long) 1e9 + 7);
        dp[x][y][k] = (int) ans;
        return dp[x][y][k];
    }
}
