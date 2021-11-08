package com.shuangpeng.Problem.p0801_0900;

public class Problem0879ProfitableSchemes {

    public int profitableSchemes0(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length, MOD = (int) 1e9 + 7;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < members) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[len][j][minProfit]) % MOD;
        }
        return sum;
    }

    public int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        int length = profit.length;
        int mod = (int) (1e9 + 7);
        int[][][] dp = new int[n + 1][length + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= length; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    int staff = group[j - 1], earn = profit[j - 1];
                    if (i >= staff) {
                        dp[i][j][k] = (dp[i][j - 1][k] + dp[i - staff][j - 1][Math.max(0, k - earn)]) % mod;
                    } else {
                        dp[i][j][k] = dp[i][j - 1][k];
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = (sum + dp[i][length][minProfit]) % mod;
        }
        return sum;
    }

    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int length = profit.length;
        int mod = (int) (1e9 + 7);
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;
        for (int i = 0; i < length; i++) {
            int members = group[i], earn = profit[i];
            for (int j = n; j >= 0; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    if (j >= members) {
                        dp[j][k] = (dp[j][k] + dp[j - members][Math.max(k - earn, 0)]) % mod;
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = (sum + dp[i][minProfit]) % mod;
        }
        return sum;
    }

    public int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {
        int length = profit.length;
        int mod = (int) (1e9 + 7);
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;
        for (int i = 0; i < length; i++) {
            int members = group[i], earn = profit[i];
            for (int j = n; j >= members; j--) {
                for (int k = 0; k <= minProfit; k++) {
                    dp[j][k] = (dp[j][k] + dp[j - members][Math.max(k - earn, 0)]) % mod;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = (sum + dp[i][minProfit]) % mod;
        }
        return sum;
    }

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        int len = group.length, MOD = (int)1e9 + 7;
        for (int i = 1; i <= len; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = n; j >= members; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % MOD;
                }
            }
        }
        return dp[n][minProfit];
    }
}
