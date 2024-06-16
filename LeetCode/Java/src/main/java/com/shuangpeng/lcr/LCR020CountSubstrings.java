package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR020CountSubstrings（回文字串）
 * @date 2024/6/16 6:09 PM
 */
public class LCR020CountSubstrings {

    public int countSubstrings(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = n;
        int[][] dp = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            dp[i][i] = 1;
            dp[i][i + 1] = cs[i] == cs[i + 1] ? 1 : 0;
            ans += dp[i][i + 1];
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = cs[i] == cs[j] ? dp[i + 1][j - 1] : 0;
                ans += dp[i][j];
            }
        }
        return ans;
    }
}
