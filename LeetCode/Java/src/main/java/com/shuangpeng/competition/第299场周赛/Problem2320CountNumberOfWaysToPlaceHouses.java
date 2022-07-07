package com.shuangpeng.competition.第299场周赛;

/**
 * @Description: Problem2320CountNumberOfWaysToPlaceHouses（统计放置房子的方式数）
 * @Date 2022/7/7 4:35 PM
 * @Version 1.0
 */
public class Problem2320CountNumberOfWaysToPlaceHouses {

    // 比赛时写法
    public int countHousePlacements0(int n) {
        int M = (int) 1e9 + 7;
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % M;
            dp[i][1] = dp[i - 1][0];
        }
        return (int) ((long) (dp[n - 1][0] + dp[n - 1][1]) * (dp[n - 1][0] + dp[n - 1][1]) % M);
    }

    public int countHousePlacements1(int n) {
        int M = (int) 1e9 + 7;
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            a = (a + b) % M;
            b = (a - b + M) % M;
        }
        int sum = (a + b) % M;
        return (int) ((long) sum * sum % M);
    }

    public int countHousePlacements(int n) {
        int M = (int) 1e9 + 7;
        int[][] arr = {{1, 1}, {1, 0}};
        int[][] ans = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = matrixMultiply(ans, arr);
            }
            arr = matrixMultiply(arr, arr);
            n >>= 1;
        }
        long sum = (long) ans[0][0] + ans[0][1];
        return (int) (sum * sum % M);
    }

    private int[][] matrixMultiply(int[][] a, int[][] b) {
        int M = (int) 1e9 + 7;
        int m = a.length, n = b[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long sum = 0;
                for (int k = 0; k < b.length; k++) {
                    sum += (long) a[i][k] * b[k][j];
                }
                ans[i][j] = (int) (sum % M);
            }
        }
        return ans;
    }
}
