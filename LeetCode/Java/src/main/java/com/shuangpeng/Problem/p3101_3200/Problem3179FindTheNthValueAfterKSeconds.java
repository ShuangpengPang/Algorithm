package com.shuangpeng.Problem.p3101_3200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3179FindTheNthValueAfterKSeconds（K秒后第N个元素的值）
 * @date 2024/6/14 6:23 PM
 */
public class Problem3179FindTheNthValueAfterKSeconds {

    private static int N = (int) 1e9 + 7;

    public int valueAfterKSeconds(int n, int k) {
        // f(n, k) = f(n, k - 1) + f(n - 1, k);
        int[][] memo = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(n, k, memo);
    }

    private int dfs(int n, int k, int[][] memo) {
        if (n == 1 || k == 0) {
            return 1;
        }
        if (memo[n][k] != -1) {
            return memo[n][k];
        }
        return memo[n][k] = (dfs(n, k - 1, memo) + dfs(n - 1, k, memo)) % N;
    }
}
