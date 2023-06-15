package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1177CanMakePalindromeFromSubstring（构建回文串检测）
 * @date 2023/6/9 10:59 AM
 */
public class Problem1177CanMakePalindromeFromSubstring {

    public List<Boolean> canMakePaliQueries0(String s, int[][] queries) {
        int n = s.length();
        int[][] dp = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            for (int j = 0; j < 26; j++) {
                dp[i + 1][j] = dp[i][j] + (c == j ? 1 : 0);
            }
        }
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) {
            int left = q[0], right = q[1] + 1, k = q[2];
            int cnt = 0;
            for (int i = 0; i < 26; i++) {
                cnt += dp[right][i] - dp[left][i] & 1;
            }
            ans.add((cnt >> 1) <= k);
        }
        return ans;
    }

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = 1 << cs[i] - 'a' ^ dp[i];
        }
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) {
            int num = dp[q[1] + 1] ^ dp[q[0]], cnt = 0;
            while (num != 0) {
                num &= num - 1;
                cnt++;
            }
            ans.add(cnt >> 1 <= q[2]);
        }
        return ans;
    }
}
