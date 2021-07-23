package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0629KInversePairsArray {

    public int kInversePairs0(int n, int k) {
        if (k == 0) {
            return 1;
        }
        final int MOD = (int) 1e9 + 7;
        int[][] dp = new int[n][k + 1];
        dp[0][0] = 1;
        int answer = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                int count = 0;
                for (int m = i - 1; m >= 0; m--) {
                    int t = j - ((i - m) << 1) + 1;
                    if (t >= 0) {
                        count += (t == 0 ? dp[m][t] : dp[m][t] << 1);
                        count -= (count >= MOD ? MOD : 0);
                    } else {
                        break;
                    }
                }
                dp[i][j] = count;
            }
            answer += dp[i][k];
            answer -= (answer >= MOD ? MOD : 0);
        }
        return answer;
    }

    public int kInversePairs1(int n, int k) {
        final int MOD = (int) 1e9 + 7;
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= 1; j--) {
                for (int m = i - 1; j - i + m >= 0 && m >= 1; m--) {
                    dp[j] += dp[j - i + m];
                    dp[j] -= (dp[j] >= MOD ? MOD : 0);
                }
            }
        }
        return dp[k];
    }

    public int kInversePairs2(int n, int k) {
        if (k == 0) {
            return 1;
        }
        final int MOD = (int) 1e9 + 7;
        long[] sum = new long[k + 1];
        Arrays.fill(sum, 1);
        for (int i = 2; i <= n; i++) {
            long[] tempSum = new long[k + 1];
            tempSum[0] = 1;
            for (int j = 1; j <= k; j++) {
                int start = Math.max(0, j - i + 1);
                long temp = (start == 0 ? sum[j] : sum[j] - sum[start - 1]) % MOD;
                tempSum[j] = tempSum[j - 1] + temp;
            }
            sum = tempSum;
        }
        return (int) (sum[k] - sum[k - 1]);
    }

    public int kInversePairs3(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        int M = 1000000007;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i * (i - 1) / 2; j++) {
                if (i == 1 && j == 0) {
                    dp[i][j] = 1;
                    break;
                } else if (j == 0)
                    dp[i][j] = 1;
                else {
                    int val = (dp[i - 1][j] + M - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % M;
                    dp[i][j] = (dp[i][j - 1] + val) % M;
                }
            }
        }
        return dp[n][k];
    }

    public int kInversePairs4(int n, int k) {
        final int MOD = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k && j <= (i * (i - 1) >> 1); j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD + MOD - (j < i ? 0 : dp[i - 1][j - i]);
                dp[i][j] -= (dp[i][j] >= MOD ? MOD : 0);
            }
        }
        return dp[n][k];
    }

    public int kInversePairs(int n, int k) {
        final int MOD = (int) 1e9 + 7;
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 2; i <= n; i++) {
            int maxJ = i * (i - 1) >> 1;
            int[] temp = new int[k + 1];
            temp[0] = 1;
            for (int j = 1; j <= k && j <= maxJ; j++) {
                temp[j] = (temp[j - 1] + dp[j]) % MOD + MOD - (j < i ? 0 : dp[j - i]);
                temp[j] -= (temp[j] >= MOD ? MOD : 0);
            }
            dp = temp;
        }
        return dp[k];
    }

//    public static void main(String[] args) {
//        Problem0629KInversePairsArray a = new Problem0629KInversePairsArray();
//        a.kInversePairs(3, 2);
//    }

    // (2, 1)
    // (3, 1, 2) 、 （2，3, 1)

    // 1,2,3  1,3,2  2,1,3  2,3,1  3,1,2  3,2,1

    // 1 2 3 4 5
    // 1 5 2 4 3
    // i, j -> j - i + j - i - 1 = 2 * (j - i) - 1
    // dp[i][j] = dp[k][j - 2 * (i - k) + 1]
}
