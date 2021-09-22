package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem1000MinimumCostToMergeStones {

    public int mergeStones0(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        final int INF = Integer.MAX_VALUE / 3;
        int[][][] dp = new int[n + 1][n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                Arrays.fill(dp[i][j], INF);
            }
            dp[i][i][1] = 0;
        }
        for (int i = n - 1; i >= 1; --i) {
            for (int j = i + 1; j <= n; ++j) {
                for (int p = Math.min(k, j - i + 1); p >= 2; --p) {
                    for (int t = i; t < j; ++t) {
                        dp[i][j][p] = Math.min(dp[i][j][p], dp[i][t][1] + dp[t + 1][j][p - 1]);
                    }
                }
                dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j][k] + preSum[j] - preSum[i - 1]);
            }
        }
        return dp[1][n][1] == INF ? -1 : dp[1][n][1];
    }

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + stones[i];
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - k + 1; i >= 1; --i) {
            dp[i][i + k - 1] = sum[i + k - 1] - sum[i - 1];
            for (int j = i + k; j <= n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int p = i; p < j; p += (k - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j]);
                }
                if ((j - i) % (k - 1) == 0) {
                    dp[i][j] += sum[j] - sum[i - 1];
                }
            }
        }
        return dp[1][n];
    }

//    public static void main(String[] args) {
//        Problem1000MinimumCostToMergeStones a = new Problem1000MinimumCostToMergeStones();
////        a.mergeStones(new int[]{3, 2, 4, 1}, 2);
//        a.mergeStones(new int[]{3, 5, 1, 2, 6}, 3);
////     3, 2, 4, 1
//    }
}
