package com.shuangpeng.Problem.p3101_3200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3179FindTheNthValueAfterKSeconds（K秒后第N个元素的值）
 * @date 2024/6/14 6:23 PM
 */
public class Problem3179FindTheNthValueAfterKSeconds {

    public int valueAfterKSeconds(int n, int k) {
        int N = (int) 1e9 + 7;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int s = 0; s < k; s++) {
            for (int i = 1; i < n; i++) {
                dp[i] = (dp[i] + dp[i - 1]) % N;
            }
        }
        return dp[n - 1];
    }
}
