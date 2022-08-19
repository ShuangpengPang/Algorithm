package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1478AllocateMailboxes（安排邮筒）
 * @Date 2022/8/19 12:02 PM
 * @Version 1.0
 */
public class Problem1478AllocateMailboxes {

    public int minDistance0(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        int[][] prefix = new int[n][n];
        for (int i = 0; i < n; i++) {
            prefix[i][i] = 0;
            for (int j = i + 1; j < n; j++) {
                prefix[i][j] = prefix[i][j - 1] + houses[j] - houses[i];
            }
        }
        int[][] suffix = new int[n][n];
        for (int j = 0; j < n; j++) {
            suffix[j][j] = 0;
            for (int i = j - 1; i >= 0; i--) {
                suffix[i][j] = suffix[i + 1][j] + houses[j] - houses[i];
            }
        }
        int INF = Integer.MAX_VALUE >> 1;
        int[][] dp = new int[n][k];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = suffix[0][i];
            for (int j = 1; j < k && j <= i; j++) {
                int c = j - 1;
                for (int p = j - 1; p < i; p++) {
                    int l = (houses[p] + houses[i]) >> 1;
                    while (houses[c + 1] <= l) {
                        c++;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + prefix[p][c] + suffix[c + 1][i]);
                }
            }
        }
        int ans = INF;
        for (int i = k - 1; i < n; i++) {
            ans = Math.min(ans, dp[i][k - 1] + prefix[i][n - 1]);
        }
        return ans;
    }

    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        int[][] cost = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = cost[i + 1][j - 1] + houses[j] - houses[i];
            }
        }
        int[][] dp = new int[n][k];
        int INF = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = cost[0][i];
            for (int j = 1; j < k && j <= i; j++) {
                for (int s = i; s >= j; s--) {
                    dp[i][j] = Math.min(dp[i][j], dp[s - 1][j - 1] + cost[s][i]);
                }
            }
        }
        return dp[n - 1][k - 1];
    }

//    public static void main(String[] args) {
//        Problem1478AllocateMailboxes a = new Problem1478AllocateMailboxes();
//        int[] nums = {1,3,13,7,6};
//        a.minDistance(nums, 2);
//    }
}

