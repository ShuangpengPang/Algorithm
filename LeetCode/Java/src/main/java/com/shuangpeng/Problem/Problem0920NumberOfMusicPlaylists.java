package com.shuangpeng.Problem;

public class Problem0920NumberOfMusicPlaylists {

    public int numMusicPlaylists0(int n, int goal, int k) {
        int[][] dp = new int[goal + 1][n + 1];
        dp[1][1] = 1;
        if (k == 0) {
            for (int i = 2; i <= goal; ++i) {
                dp[i][1] = 1;
            }
        }
        final int M = (int) 1e9 + 7;
        for (int i = 2; i <= goal; ++i) {
            for (int j = 2; j <= i && j <= n; ++j) {
                dp[i][j] = (int) (((long) dp[i - 1][j - 1] * j + (long) dp[i - 1][j] * Math.max(j - k, 0)) % M);
            }
        }
        return dp[goal][n];
    }

    public int numMusicPlaylists1(int n, int goal, int k) {
        int[][] dp = new int[goal + 1][n + 1];
        dp[0][0] = 1;
        final int M = (int) 1e9 + 7;
        for (int i = 1; i <= goal; ++i) {
            for (int j = 1; j <= i && j <= n; ++j) {
                dp[i][j] = (int) (((long) dp[i - 1][j - 1] * j + (long) dp[i - 1][j] * Math.max(j - k, 0)) % M);
            }
        }
        return dp[goal][n];
    }

    public int numMusicPlaylists(int n, int goal, int k) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        final int M = (int) 1e9 + 7;
        for (int i = 1; i <= goal; ++i) {
            for (int j = Math.min(i, n); j >= 1; --j) {
                dp[j] = (int) (((long) dp[j - 1] * j + (long) dp[j] * Math.max(j - k, 0)) % M);
            }
            dp[0] = 0;
        }
        return dp[n];
    }

//    public static void main(String[] args) {
//        Problem0920NumberOfMusicPlaylists a = new Problem0920NumberOfMusicPlaylists();
//        a.numMusicPlaylists(2, 4, 1);
//    }
}
