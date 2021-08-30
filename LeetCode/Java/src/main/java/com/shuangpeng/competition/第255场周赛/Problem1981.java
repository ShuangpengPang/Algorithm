package com.shuangpeng.competition.第255场周赛;

import java.util.Arrays;

public class Problem1981 {

    public int minimizeTheDifference0(int[][] mat, int target) {
        int m = mat.length, n = mat[0].length;
        boolean[] dp = {true};
        int maxValue = 0;
        for (int i = 0; i < m; ++i) {
            int rowMax = Arrays.stream(mat[i]).max().getAsInt();
            boolean[] temp = new boolean[maxValue + rowMax + 1];
            for (int j = 0; j < n; ++j) {
                for (int v = mat[i][j]; v <= mat[i][j] + maxValue; ++v) {
                    temp[v] = temp[v] || dp[v - mat[i][j]];
                }
            }
            dp = temp;
            maxValue += rowMax;
        }
        int length = dp.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i - target < ans && i < length; ++i) {
            if (dp[i]) {
                ans = Math.min(ans, Math.abs(i - target));
            }
        }
        return ans;
    }

    public int minimizeTheDifference1(int[][] mat, int target) {
        int m = mat.length, n = mat[0].length;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        final int INF = Integer.MAX_VALUE >> 1;
        int minValue = INF;
        for (int i = 0; i < m; ++i) {
            boolean[] temp = new boolean[target + 1];
            int tempMin = INF;
            for (int s = 0; s <= target; ++s) {
                for (int j = 0; j < n; ++j) {
                    if (s >= mat[i][j]) {
                        temp[s] = temp[s] | dp[s - mat[i][j]];
                    }
                    if (dp[s] && s + mat[i][j] > target) {
                        tempMin = Math.min(tempMin, s + mat[i][j]);
                    }
                    tempMin = Math.min(tempMin, minValue + mat[i][j]);
                }
            }
            minValue = tempMin;
            dp = temp;
        }
        int ans = minValue - target;
        for (int i = 0; i <= target; ++i) {
            if (dp[i]) {
                ans = Math.min(ans, target - i);
            }
        }
        return ans;
    }

    public int minimizeTheDifference(int[][] mat, int target) {
        int m = mat.length, n = mat[0].length;
        final int INF = Integer.MAX_VALUE >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        int largest = INF;
        for (int i = 0; i < m; ++i) {
            boolean[] temp = new boolean[target + 1];
            int nextLargest = INF;
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k <= target; ++k) {
                    if (dp[k]) {
                        if (k + mat[i][j] <= target) {
                            temp[k + mat[i][j]] = true;
                        } else {
                            nextLargest = Math.min(nextLargest, k + mat[i][j]);
                        }
                    }
                }
                nextLargest = Math.min(nextLargest, largest + mat[i][j]);
            }
            largest = nextLargest;
            dp = temp;
        }
        int ans = largest - target;
        for (int i = target; i >= 0; --i) {
            if (dp[i]) {
                ans = Math.min(ans, target - i);
                break;
            }
        }
        return ans;
    }
}
