package com.shuangpeng.Problem.p1701_1800;

/**
 * @Description: Problem1799MaximizeScoreAfterNOperations（N次操作后的最大分数和）
 * @Date 2022/10/13 3:29 PM
 * @Version 1.0
 */
public class Problem1799MaximizeScoreAfterNOperations {

    public int maxScore(int[] nums) {
        int n = nums.length, N = 1 << n;
        int[] g = new int[N];
        for (int i = 1; i < N; i++) {
            int a = -1, b = -1;
            boolean valid = true;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    if (a == -1) {
                        a = nums[j];
                    } else if (b == -1) {
                        b = nums[j];
                    } else {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) {
                g[i] = gcd(a, b);
            }
        }
        int[] dp = new int[N];
        for (int i = 1; i < N; i++) {
            int cnt = Integer.bitCount(i);
            if ((cnt & 1) == 1) {
                continue;
            }
            int h = cnt >> 1;
            for (int j = i; j > 0; j = (j - 1) & i) {
                if (g[j] == 0) {
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[i ^ j] + h * g[j]);
            }
        }
        return dp[N - 1];
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

class Problem1799MaximizeScoreAfterNOperations0 {

    public int maxScore(int[] nums) {
        int n = nums.length, N = 1 << n;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                g[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int[] dp = new int[N];
        for (int i = 1; i < N; i++) {
            int cnt = Integer.bitCount(i);
            if ((cnt & 1) == 1) {
                continue;
            }
            cnt >>= 1;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (((i >> k) & 1) == 0) {
                        continue;
                    }
                    dp[i] = Math.max(dp[i], dp[i ^ (1 << j) ^ (1 << k)] + cnt * g[j][k]);
                }
            }
        }
        return dp[N - 1];
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}