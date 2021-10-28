package com.shuangpeng.Problem.p0001_0100;

import java.util.Arrays;

public class Problem0062UniquePath {

    public int uniquePaths0(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    public int uniquePaths1(int m, int n) {
        int[] preLine = new int[n];
        Arrays.fill(preLine, 1);
        for (int i = 1; i < m; i++) {
            int current = preLine[0];
            for (int j = 1; j < n; j++) {
                preLine[j] = preLine[j] + current;
                current = preLine[j];
            }
        }
        return preLine[n - 1];
    }

    // 排列组合
    public int uniquePaths(int m, int n) {
        int min = Math.min(m, n) - 1;
        long result = 1;
        int steps = m + n - 2;
        for (int i = 0; i < min; i++) {
            if (result % (i + 1) == 0) {
                result = result / (i + 1) * (steps - i);
            } else if ((steps - i) % (i + 1) == 0) {
                result = (steps - i) / (i + 1) * result;
            } else {
                result = (steps - i) * result / (i + 1);
            }
        }
        return (int) result;
    }

//    public static void main(String[] args) {
//        Problem0062UniquePath a = new Problem0062UniquePath();
//        a.uniquePaths(9, 8);
//    }
}
