package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2266CountNumberOfTexts（统计打字方案数）
 * @date 2023/11/19 6:32 PM
 */
public class Problem2266CountNumberOfTexts {

    public int countTexts(String pressedKeys) {
        int n = pressedKeys.length(), M = (int) 1e9 + 7;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            char c = pressedKeys.charAt(i - 1);
            int cnt = c == '7' || c == '9' ? 4 : 3;
            for (int j = i - 1; j >= 0 && i - j <= cnt && pressedKeys.charAt(j) == c; j--) {
                dp[i] = (dp[i] + dp[j]) % M;
            }
        }
        return dp[n];
    }
}
