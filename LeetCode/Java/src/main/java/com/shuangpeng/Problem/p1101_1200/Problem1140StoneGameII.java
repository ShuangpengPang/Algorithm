package com.shuangpeng.Problem.p1101_1200;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 石子游戏II（1140）
 * @date 2023/3/1 6:16 PM
 **/
public class Problem1140StoneGameII {

    public int stoneGameII0(int[] piles) {
        int n = piles.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            preSum[i] = preSum[i - 1] + piles[i - 1];
        }
        return getMaxStone(preSum, 1, 1, new HashMap<>());
    }

    private int getMaxStone(int[] preSum, int i, int m, Map<Integer, Integer> memo) {
        int n = preSum.length;
        if ((m << 1) >= n - i) {
            return preSum[n - 1] - preSum[i - 1];
        }
        int key = i * 101 + m;
        int backup = memo.getOrDefault(key, -1);
        if (backup != -1) {
            return backup;
        }
        int maxValue = 0;
        int sum = preSum[n - 1] - preSum[i - 1];
        for (int j = 1; j <= (m << 1); ++j) {
            maxValue = Math.max(maxValue, sum - getMaxStone(preSum, i + j, Math.max(j, m), memo));
        }
        memo.put(key, maxValue);
        return maxValue;
    }

    public int stoneGameII1(int[] piles) {
        int n = piles.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + piles[i];
        }
        return dfs(preSum, 1, 2, new HashMap<>());
    }

    private int dfs(int[] preSum, int start, int length, Map<Integer, Integer> memo) {
        int n = preSum.length, sum = preSum[n - 1] - preSum[start - 1];
        if (start + length >= n) {
            return sum;
        }
        int key = start << 10 | length;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = 0;
        for (int i = 1; i <= length; i++) {
            ans = Math.max(ans, sum - dfs(preSum, start + i, Math.max(i << 1, length), memo));
        }
        memo.put(key, ans);
        return ans;
    }

    public int stoneGameII(int[] piles) {
        int n = piles.length, h = (n + 1) >> 1;
        int[][] dp = new int[n][h + 1];
        for (int i = n - 1, s = 0; i >= 0; i--) {
            s += piles[i];
            for (int j = 1; j <= h; j++) {
                if (i + (j << 1) >= n) {
                    dp[i][j] = s;
                } else {
                    for (int k = 1; k <= (j << 1); k++) {
                        dp[i][j] = Math.max(dp[i][j], s - dp[i + k][Math.min(h, Math.max(j, k))]);
                    }
                }
            }
        }
        return dp[0][1];
    }

    // 错误做法：
    public int stoneGameII100(int[] piles) {
        int n = piles.length;
        if (n == 1) {
            return piles[0];
        }
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][n - 1] = piles[i] + (i + 1 < n ? dp[i + 1][n - 1] : 0);
            for (int j = n - 2; j >= i; j--) {
                int m = (j - i + 1) << 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = j + 1; k < n && k - j <= m; k++) {
                    // 这个地方有问题，求出的是dp[i][j]的最小值
                    dp[i][j] = Math.min(dp[i][j], dp[i][n - 1] - dp[j + 1][k]);
                }
            }
        }
        return Math.max(dp[0][0], dp[0][1]);
    }
}
