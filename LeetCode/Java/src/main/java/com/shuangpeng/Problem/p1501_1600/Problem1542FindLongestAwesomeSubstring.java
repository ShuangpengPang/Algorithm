package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;

/**
 * @Description: Problem1542FindLongestAwesomeSubstring（找出最长的超赞子字符串）
 * @Date 2022/8/30 10:37 AM
 * @Version 1.0
 */
public class Problem1542FindLongestAwesomeSubstring {

    public int longestAwesome(String s) {
        int n = s.length();
        int[] dp = new int[1 << 10];
        Arrays.fill(dp, n);
        dp[0] = 0;
        int ans = 0, hash = 0;
        for (int i = 0; i < n; i++) {
            hash ^= 1 << (s.charAt(i) - '0');
            int l = Math.max(i - dp[hash] + 1, 1);
            for (int j = 0; j < 10; j++) {
                l = Math.max(l, i - dp[hash ^ (1 << j)] + 1);
            }
            ans = Math.max(ans, l);
            dp[hash] = Math.min(dp[hash], i + 1);
        }
        return ans;
    }
}

