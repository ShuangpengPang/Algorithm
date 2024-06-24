package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR020CountSubstrings（回文字串）
 * @date 2024/6/16 6:09 PM
 */
public class LCR020CountSubstrings {

    public int countSubstrings0(String s) {
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

    public int countSubstrings1(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        for (int i = 0; i < (n << 1) - 1; i++) {
            int l = i >> 1, r = i + 1 >> 1;
            while (l >= 0 && r < n && cs[l] == cs[r]) {
                ans++;
                l--;
                r++;
            }
        }
        return ans;
    }

    public int countSubstrings(String s) {
        char[] arr = s.toCharArray();
        int m = arr.length, n = (m << 1) + 1;
        char[] cs = new char[n];
        cs[0] = '#';
        for (int i = 1, j = 0; i < n; i += 2, j++) {
            cs[i] = arr[j];
            cs[i + 1] = '#';
        }
        int[] dp = new int[n];
        int ans = 0, center = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int j = (center << 1) - i;
            dp[i] = i < center + dp[center] && j > 0 ? Math.min(center + dp[center] - i, dp[j]) : 1;
            while (i - dp[i] >= 0 && i + dp[i] < n && cs[i - dp[i]] == cs[i + dp[i]]) {
                dp[i]++;
            }
            if (i + dp[i] > center + dp[i]) {
                center = i;
            }
            ans += dp[i] >> 1;
        }
        return ans;
    }
}
