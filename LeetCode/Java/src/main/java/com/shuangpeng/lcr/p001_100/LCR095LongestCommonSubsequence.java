package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR095LongestCommonSubsequence
 * @date 2024/7/5 3:48 PM
 */
public class LCR095LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        char[] cs1 = text1.toCharArray(), cs2 = text2.toCharArray();
        int n1 = cs1.length, n2 = cs2.length;
        int[] dp = new int[n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1, pre = 0; j <= n2; j++) {
                int tmp = dp[j];
                dp[j] = cs1[i - 1] == cs2[j - 1] ? pre + 1 : Math.max(dp[j], dp[j - 1]);
                pre = tmp;
            }
        }
        return dp[n2];
    }
}
