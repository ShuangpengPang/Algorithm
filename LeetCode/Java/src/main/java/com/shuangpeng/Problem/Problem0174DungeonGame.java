package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0174DungeonGame {

    public int calculateMinimumHP0(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = dungeon[i][j] > 0 ? 1 : 1 - dungeon[i][j];
                } else if (i == m - 1) {
                    dp[i][j] = dp[i][j + 1] - dungeon[i][j];
                    dp[i][j] = dp[i][j] > 0 ? dp[i][j] : 1;
                } else if (j == n - 1) {
                    dp[i][j] = dp[i + 1][j] - dungeon[i][j];
                    dp[i][j] = dp[i][j] > 0 ? dp[i][j] : 1;
                } else {
                    dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j];
                    dp[i][j] = dp[i][j] > 0 ? dp[i][j] : 1;
                }
            }
        }
        return dp[0][0];
    }

    public int calculateMinimumHP1(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[j] = Math.min(dp[j], dp[j + 1]) - dungeon[i][j];
                dp[j] = Math.max(dp[j], 1);
            }
        }
        return dp[0];
    }
}
