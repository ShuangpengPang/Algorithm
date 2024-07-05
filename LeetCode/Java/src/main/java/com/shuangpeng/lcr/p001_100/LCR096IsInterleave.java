package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR096IsInterleave（交错字符串）
 * @date 2024/7/5 11:25 AM
 */
public class LCR096IsInterleave {

    public boolean isInterleave0(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if (n1 + n2 != n3) {
            return false;
        }
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray(), cs3 = s3.toCharArray();
        boolean[][] dp = new boolean[2][n2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n2 && dp[0][i - 1]; i++) {
            dp[0][i] = cs2[i - 1] == cs3[i - 1];
        }
        for (int i = 1; i <= n1; i++) {
            boolean[] cur = dp[i & 1], pre = dp[(i & 1) ^ 1];
            for (int j = 0, k = i - 1; j <= n2; j++, k++) {
                cur[j] = cs1[i - 1] == cs3[k] && pre[j] || j > 0 && cs2[j - 1] == cs3[k] && cur[j - 1];
            }
        }
        return dp[n1 & 1][n2];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if (n1 + n2 != n3) {
            return false;
        }
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray(), cs3 = s3.toCharArray();
        boolean[] dp = new boolean[n2 + 1];
        dp[0] = true;
        for (int i = 0; i <= n1; i++) {
            for (int j = 0, k = i - 1; j <= n2; j++, k++) {
                if (i > 0) {
                    dp[j] = dp[j] && cs1[i - 1] == cs3[k];
                }
                if (j > 0) {
                    dp[j] = dp[j] || dp[j - 1] && cs2[j - 1] == cs3[k];
                }
            }
        }
        return dp[n2];
    }
}
