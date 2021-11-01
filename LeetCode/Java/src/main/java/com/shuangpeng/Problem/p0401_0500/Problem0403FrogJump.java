package com.shuangpeng.Problem.p0401_0500;

import java.util.Arrays;

public class Problem0403FrogJump {

    private Boolean[][] rec;

    public boolean canCross0(int[] stones) {
        int n = stones.length;
        rec = new Boolean[n][n];
        return dfs(stones, 0, 0);
    }

    private boolean dfs(int[] stones, int i, int lastDis) {
        if (i == stones.length - 1) {
            return true;
        }
        if (rec[i][lastDis] != null) {
            return rec[i][lastDis];
        }

        for (int curDis = lastDis - 1; curDis <= lastDis + 1; curDis++) {
            if (curDis > 0) {
                int j = Arrays.binarySearch(stones, i + 1, stones.length, curDis + stones[i]);
                if (j >= 0 && dfs(stones, j, curDis)) {
                    return rec[i][lastDis] = true;
                }
            }
        }
        return rec[i][lastDis] = false;
    }

    public boolean canCross1(int[] stones) {
        int n = stones.length;
        return dfs(stones, 0, 0, new int[n][n]);
    }

    private boolean dfs(int[] stones, int i, int gap, int[][] memo) {
        if (i < 0) {
            return false;
        }
        int n = stones.length;
        if (i == n - 1) {
            return true;
        }
        if (memo[i][gap] != 0) {
            return memo[i][gap] == 1;
        }
        for (int j = gap - 1; j <= gap + 1; j++) {
            if (j > 0 && dfs(stones, Arrays.binarySearch(stones, i + 1, n, stones[i] + j), j, memo)) {
                memo[i][gap] = 1;
                return true;
            }
        }
        memo[i][gap] = -1;
        return false;
    }

    public boolean canCross2(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int key = stones[i] - j;
                if (key < 0) {
                    break;
                }
                int index = Arrays.binarySearch(stones, 0, i, key);
                if (index < 0) {
                    continue;
                }
                for (int k = -1; k <= 1; k++) {
                    if (j + k >= 0 && j + k < n) {
                        dp[i][j] = dp[i][j] || dp[index][j + k];
                        if (dp[i][j]) {
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dp[n -1][i]) {
                return true;
            }
        }
        return false;
    }

    public boolean canCross(int[] stones) {
        int n = stones.length;
        for (int i = 1; i < n; i++) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        boolean[][] dp = new boolean[n][n + 1];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int k = stones[i] - stones[j];
                if (k > i) {
                    break;
                }
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        Problem0403FrogJump a = new Problem0403FrogJump();
//        a.canCross(new int[]{0,1,3,5,6,8,12,17});
//    }
}
