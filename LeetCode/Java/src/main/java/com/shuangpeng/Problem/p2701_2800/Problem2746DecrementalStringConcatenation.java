package com.shuangpeng.Problem.p2701_2800;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2746DecrementalStringConcatenation（字符串连接删减字母）
 * @date 2023/8/5 8:06 PM
 */
public class Problem2746DecrementalStringConcatenation {

    public int minimizeConcatenatedLength(String[] words) {
        int n = words.length, N = Integer.MAX_VALUE >> 1;
        int[][][] dp = new int[2][26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dp[0][i], N);
        }
        String first = words[0];
        dp[0][first.charAt(0) - 'a'][first.charAt(first.length() - 1) - 'a'] = first.length();
        for (int i = 1; i < n; i++) {
            int m = words[i].length(), c1 = words[i].charAt(0) - 'a', c2 = words[i].charAt(m - 1) - 'a';
            int idx = i & 1, p = idx ^ 1;
            for (int j = 0; j < 26; j++) {
                Arrays.fill(dp[idx][j], N);
            }
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    dp[idx][j][c2] = Math.min(dp[idx][j][c2], dp[p][j][k] + m - (k == c1 ? 1 : 0));
                    dp[idx][c1][k] = Math.min(dp[idx][c1][k], dp[p][j][k] + m - (j == c2 ? 1 : 0));
                }
            }
        }
        int ans = N, idx = n - 1 & 1;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                ans = Math.min(ans, dp[idx][i][j]);
            }
        }
        return ans;
    }
}
